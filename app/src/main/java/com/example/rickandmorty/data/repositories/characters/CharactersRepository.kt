package com.example.rickandmorty.data.repositories.characters

import com.example.rickandmorty.data.datasource.characters.database.models.CharactersDatabaseSourceInterface
import com.example.rickandmorty.data.datasource.characters.database.models.toEntity
import com.example.rickandmorty.data.datasource.characters.remote.dto.CharactersRemoteDataSourceInterface
import com.example.rickandmorty.data.datasource.characters.remote.dto.toEntity
import com.example.rickandmorty.di.AppContainer
import com.example.rickandmorty.domain.entities.characters.CharactersEntity
import com.example.rickandmorty.manager.network.NetworkMonitorInterface

interface CharactersRepositoryInterface {
    suspend fun getCharacters(page: Int): CharactersEntity
    suspend fun searchCharacters(page: Int, name: String): CharactersEntity
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

    override suspend fun getCharacters(page: Int): CharactersEntity {
        if (page == 1 && !existsConnection) {
            val realmList = databaseSource.getCharacters()
            return CharactersEntity(realmList.map { it.toEntity },false)
        }
        // Get values from network
        val res = remoteDataSource.getCharacters(page)
        // Data persistance
        databaseSource.saveCharacters(res?.results?.map { it.toEntity } ?: arrayListOf())
        return res?.toEntity ?: run {
            CharactersEntity(
                characters = arrayListOf(),
                hasNextPage = false
            )
        }
    }

    override suspend fun searchCharacters(page: Int,
                                 name: String): CharactersEntity {
        if (!existsConnection) {
            val realmList = databaseSource.searchCharacters(name)
            return CharactersEntity(realmList.map { it.toEntity },false)
        }
        // Search values from network
        val res = remoteDataSource.searchCharacters(page, name)
        // Data persistance
        databaseSource.saveCharacters(res?.results?.map { it.toEntity } ?: arrayListOf())
        return res?.toEntity ?: run {
            CharactersEntity(
                characters = arrayListOf(),
                hasNextPage = false
            )
        }
    }
}
