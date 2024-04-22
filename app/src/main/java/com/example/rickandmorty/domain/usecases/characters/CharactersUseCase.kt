package com.example.rickandmorty.domain.usecases.characters

import com.example.rickandmorty.data.repositories.characters.CharactersRepositoryInterface
import com.example.rickandmorty.domain.entities.characters.CharactersEntity
import com.example.rickandmorty.domain.entities.characters.CharactersEntityWithException
import java.lang.Exception

interface CharactersUseCaseInterface {
    suspend fun getCharacters(page: Int): CharactersEntityWithException<CharactersEntity, Exception?>
    suspend fun searchCharacters(page: Int, name: String): CharactersEntityWithException<CharactersEntity, Exception?>
}

class CharactersUseCase(
    private val repository: CharactersRepositoryInterface
): CharactersUseCaseInterface {

    override suspend fun getCharacters(page: Int): CharactersEntityWithException<CharactersEntity, Exception?> {
        return repository.getCharacters(page)
    }

    override suspend fun searchCharacters(page: Int,
                                 name: String): CharactersEntityWithException<CharactersEntity, Exception?> {
        return repository.searchCharacters(page, name)
    }
}