package com.example.rickandmorty.data.datasource.characters.database.models

import com.example.rickandmorty.data.datasource.characters.remote.dto.CharactersWithException
import com.example.rickandmorty.domain.entities.characters.CharacterEntity
import io.realm.kotlin.Realm
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.ext.query
import java.lang.Exception

interface CharactersDatabaseSourceInterface {
    fun saveCharacters(characterList: List<CharacterEntity>): Exception?
    fun getCharacters(): CharactersWithException<List<RCharacter>, Exception?>
    fun searchCharacters(name: String): CharactersWithException<List<RCharacter>, Exception?>
}

class CharactersDatabaseSource(
    val realm: Realm
): CharactersDatabaseSourceInterface {

    override fun saveCharacters(characterList: List<CharacterEntity>): Exception? {
        try {
            realm.writeBlocking {
                characterList.forEach {
                    val character = RCharacter().apply {
                        id = it.id
                        name = it.name
                        status = it.status
                        species = it.species
                        type = it.type
                        gender = it.gender
                        image = it.image
                        val newLcation = RLocation().apply {
                            name = it.location.name
                            url = it.location.url
                        }
                        location = newLcation
                        episode.addAll(it.episode)
                    }
                    copyToRealm(character, UpdatePolicy.ALL)
                }
            }
            return null
        } catch (e: Exception) {
            return e
        }
    }

    override fun getCharacters(): CharactersWithException<List<RCharacter>, Exception?>{
        return try {
            val res = realm.query<RCharacter>().find()
            CharactersWithException(res, null)
        } catch (e: Exception) {
            CharactersWithException(arrayListOf(), e)
        }
    }

    override fun searchCharacters(name: String): CharactersWithException<List<RCharacter>, Exception?> {
        return try {
            val res = realm.query<RCharacter>("name CONTAINS[c] $0", name).find()
            CharactersWithException(res, null)
        } catch (e: Exception) {
            CharactersWithException(arrayListOf(), e)
        }
    }
}