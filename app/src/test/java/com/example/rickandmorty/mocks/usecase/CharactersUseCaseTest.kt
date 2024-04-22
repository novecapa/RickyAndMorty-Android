package com.example.rickandmorty.mocks.usecase

import com.example.rickandmorty.data.repositories.characters.CharactersRepositoryInterface
import com.example.rickandmorty.domain.entities.characters.CharactersEntity
import com.example.rickandmorty.domain.entities.characters.CharactersEntityWithException
import com.example.rickandmorty.domain.usecases.characters.CharactersUseCaseInterface
import java.lang.Exception

class CharactersUseCaseTest(
    private val repository: CharactersRepositoryInterface
): CharactersUseCaseInterface {
    override suspend fun getCharacters(page: Int): CharactersEntityWithException<CharactersEntity, Exception?> {
        return repository.getCharacters(page)
    }

    override suspend fun searchCharacters(
        page: Int,
        name: String
    ): CharactersEntityWithException<CharactersEntity, Exception?> {
        return repository.searchCharacters(page, name)
    }
}