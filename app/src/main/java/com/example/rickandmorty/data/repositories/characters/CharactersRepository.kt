package com.example.rickandmorty.data.repositories.characters

import com.example.rickandmorty.data.datasource.characters.remote.dto.CharactersRemoteDataSource
import com.example.rickandmorty.data.datasource.characters.remote.dto.toEntity
import com.example.rickandmorty.domain.entities.characters.CharactersEntity

class CharactersRepository(
    private val remoteDataSource: CharactersRemoteDataSource
) {

    suspend fun getCharacters(page: Int): CharactersEntity {
        val res = remoteDataSource.getCharacters(page)
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
