package com.example.rickandmorty.domain.usecases.characters

import com.example.rickandmorty.data.repositories.characters.CharactersRepository
import com.example.rickandmorty.domain.entities.characters.CharactersEntity

class CharactersUseCase(
    private val repository: CharactersRepository
) {

    suspend fun getCharacters(page: Int): CharactersEntity {
        return repository.getCharacters(page)
    }

    suspend fun searchCharacters(page: Int,
                                 name: String): CharactersEntity {
        return repository.searchCharacters(page, name)
    }
}