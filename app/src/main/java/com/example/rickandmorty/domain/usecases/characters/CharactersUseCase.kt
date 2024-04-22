package com.example.rickandmorty.domain.usecases.characters

import com.example.rickandmorty.data.repositories.characters.CharactersRepositoryInterface
import com.example.rickandmorty.domain.entities.characters.CharactersEntity

interface CharactersUseCaseInterface {
    suspend fun getCharacters(page: Int): CharactersEntity
    suspend fun searchCharacters(page: Int, name: String): CharactersEntity
}

class CharactersUseCase(
    private val repository: CharactersRepositoryInterface
): CharactersUseCaseInterface {

    override suspend fun getCharacters(page: Int): CharactersEntity {
        return repository.getCharacters(page)
    }

    override suspend fun searchCharacters(page: Int,
                                 name: String): CharactersEntity {
        return repository.searchCharacters(page, name)
    }
}