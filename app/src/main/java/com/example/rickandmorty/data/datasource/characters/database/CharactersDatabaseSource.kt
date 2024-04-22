package com.example.rickandmorty.data.datasource.characters.database.models

import com.example.rickandmorty.domain.entities.characters.CharacterEntity
import io.realm.kotlin.Realm
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.ext.query
import java.lang.Exception

class CharactersDatabaseSource(
    val realm: Realm
) {

    fun saveCharacters(characterList: List<CharacterEntity>) {
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
        } catch (e: Exception) {
            // TODO: Throw error
        }
    }

    fun getCharacters(): List<RCharacter> {
        return try {
            realm.query<RCharacter>().find()
        } catch (e: Exception) {
            arrayListOf()
        }
    }

    fun searchCharacters(name: String): List<RCharacter> {
        return try {
            realm.query<RCharacter>("name CONTAINS[c] $0", name).find()
        } catch (e: Exception) {
            arrayListOf()
        }
    }
}