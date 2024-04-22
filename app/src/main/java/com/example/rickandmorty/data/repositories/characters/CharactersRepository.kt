package com.example.rickandmorty.data.repositories.characters

import com.example.rickandmorty.data.datasource.characters.database.models.CharactersDatabaseSource
import com.example.rickandmorty.data.datasource.characters.remote.dto.CharactersRemoteDataSource
import com.example.rickandmorty.data.datasource.characters.remote.dto.toEntity
import com.example.rickandmorty.domain.entities.characters.CharactersEntity

class CharactersRepository(
    private val remoteDataSource: CharactersRemoteDataSource,
    private val databaseSource: CharactersDatabaseSource
) {

    suspend fun getCharacters(page: Int): CharactersEntity {
        val res = remoteDataSource.getCharacters(page)
        databaseSource.saveCharacters(res?.results?.map { it.toEntity } ?: arrayListOf())
        return res?.toEntity ?: run {
            CharactersEntity(
                characters = arrayListOf(),
                hasNextPage = false
            )
        }
    }

    suspend fun searchCharacters(page: Int,
                                 name: String): CharactersEntity {
        val res = remoteDataSource.searchCharacters(page, name)
        return res?.toEntity ?: run {
            CharactersEntity(
                characters = arrayListOf(),
                hasNextPage = false
            )
        }
    }
}
