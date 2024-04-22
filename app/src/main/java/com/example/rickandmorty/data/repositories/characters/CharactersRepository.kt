package com.example.rickandmorty.data.repositories.characters

import com.example.rickandmorty.data.datasource.characters.database.models.CharactersDatabaseSourceInterface
import com.example.rickandmorty.data.datasource.characters.database.models.toEntity
import com.example.rickandmorty.data.datasource.characters.remote.dto.CharactersRemoteDataSourceInterface
import com.example.rickandmorty.data.datasource.characters.remote.dto.hasNextPage
import com.example.rickandmorty.data.datasource.characters.remote.dto.toEntity
import com.example.rickandmorty.di.AppContainer
import com.example.rickandmorty.domain.entities.characters.CharactersEntity
import com.example.rickandmorty.domain.entities.characters.CharactersEntityWithException
import com.example.rickandmorty.manager.network.NetworkMonitorInterface
import java.lang.Exception

interface CharactersRepositoryInterface {
    suspend fun getCharacters(page: Int): CharactersEntityWithException<CharactersEntity, Exception?>
    suspend fun searchCharacters(page: Int, name: String): CharactersEntityWithException<CharactersEntity, Exception?>
}

class CharactersRepository(
    private val remoteDataSource: CharactersRemoteDataSourceInterface,
    private val databaseSource: CharactersDatabaseSourceInterface,
    private val networkMonitor: NetworkMonitorInterface
): CharactersRepositoryInterface {

    private val existsConnection: Boolean
        get() {
            val context = AppContainer.context ?: return false
            return networkMonitor.isNetworkAvailable(context)
        }

    private fun emptyEntity(exception: Exception?): CharactersEntityWithException<CharactersEntity, Exception?>  {
        return CharactersEntityWithException(
            CharactersEntity(
                characters = arrayListOf(),
                hasNextPage = false
            ), exception)
    }

    override suspend fun getCharacters(page: Int): CharactersEntityWithException<CharactersEntity, Exception?> {
        if (page == 1 && !existsConnection) {
            val res = databaseSource.getCharacters()
            return if (res.error == null) {
                CharactersEntityWithException(CharactersEntity(res.result.map { it.toEntity }, false), null)
            } else {
                emptyEntity(res.error)
            }
        }
        // Get values from network
        val res = remoteDataSource.getCharacters(page)
        res.result?.let {
            databaseSource.saveCharacters(it.results.map { it.toEntity })
            return CharactersEntityWithException(
                CharactersEntity(it.results.map { it.toEntity }, it.info.hasNextPage),
                null
            )
        } ?: run {
            return emptyEntity(res.error)
        }
    }

    override suspend fun searchCharacters(page: Int,
                                 name: String): CharactersEntityWithException<CharactersEntity, Exception?> {
        if (!existsConnection) {
            val res = databaseSource.searchCharacters(name)
            return if (res.error == null) {
                CharactersEntityWithException(CharactersEntity(res.result.map { it.toEntity }, false), null)
            } else {
                emptyEntity(res.error)
            }
        }
        // Get values from network
        val res = remoteDataSource.searchCharacters(page, name)
        res.result?.let {
            databaseSource.saveCharacters(it.results.map { it.toEntity })
            return CharactersEntityWithException(
                CharactersEntity(it.results.map { it.toEntity }, it.info.hasNextPage),
                null
            )
        } ?: run {
            return emptyEntity(res.error)
        }
    }
}
