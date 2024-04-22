package com.example.rickandmorty.mocks.datasource

import com.example.rickandmorty.data.datasource.characters.remote.dto.CharactersDTO
import com.example.rickandmorty.data.datasource.characters.remote.dto.CharactersRemoteDataSourceInterface
import com.example.rickandmorty.data.datasource.characters.remote.dto.CharactersWithException
import java.lang.Exception

class CharactersRemoteDataSourceTest: CharactersRemoteDataSourceInterface {
    override suspend fun getCharacters(page: Int): CharactersWithException<CharactersDTO?, Exception?> {
        TODO("Not yet implemented")
    }

    override suspend fun searchCharacters(
        page: Int,
        name: String
    ): CharactersWithException<CharactersDTO?, Exception?> {
        TODO("Not yet implemented")
    }
}