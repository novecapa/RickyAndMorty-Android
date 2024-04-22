package com.example.rickandmorty.mocks.datasource

import com.example.rickandmorty.data.datasource.characters.database.models.CharactersDatabaseSourceInterface
import com.example.rickandmorty.data.datasource.characters.database.models.RCharacter
import com.example.rickandmorty.data.datasource.characters.remote.dto.CharactersWithException
import com.example.rickandmorty.domain.entities.characters.CharacterEntity
import java.lang.Exception

class CharactersDatabaseSourceTest: CharactersDatabaseSourceInterface {
    override fun saveCharacters(characterList: List<CharacterEntity>): Exception? {
        TODO("Not yet implemented")
    }

    override fun getCharacters(): CharactersWithException<List<RCharacter>, Exception?> {
        TODO("Not yet implemented")
    }

    override fun searchCharacters(name: String): CharactersWithException<List<RCharacter>, Exception?> {
        TODO("Not yet implemented")
    }
}