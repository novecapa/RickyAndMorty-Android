package com.example.rickandmorty.mocks.repository

import com.example.rickandmorty.data.datasource.characters.database.models.CharactersDatabaseSourceInterface
import com.example.rickandmorty.data.datasource.characters.remote.dto.CharactersRemoteDataSourceInterface
import com.example.rickandmorty.data.repositories.characters.CharactersRepositoryInterface
import com.example.rickandmorty.domain.entities.characters.CharactersEntity
import com.example.rickandmorty.domain.entities.characters.CharactersEntityWithException
import java.lang.Exception

class CharactersRepositoryTest(
    private val remoteDataSource: CharactersRemoteDataSourceInterface,
    private val databaseSource: CharactersDatabaseSourceInterface,
): CharactersRepositoryInterface {
    override suspend fun getCharacters(page: Int): CharactersEntityWithException<CharactersEntity, Exception?> {
        TODO("Not yet implemented")
    }

    override suspend fun searchCharacters(
        page: Int,
        name: String
    ): CharactersEntityWithException<CharactersEntity, Exception?> {
        TODO("Not yet implemented")
    }
}