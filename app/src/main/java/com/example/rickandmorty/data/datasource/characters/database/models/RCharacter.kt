package com.example.rickandmorty.data.datasource.characters.database.models

import com.example.rickandmorty.domain.entities.characters.CharacterEntity
import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class RCharacter: RealmObject {
    @PrimaryKey
    var id: Int = -1

    var name: String = ""
    var status: String = ""
    var species: String = ""
    var type: String = ""
    var gender: String = ""
    var image: String = ""
    var location: RLocation? = null
    var episode: RealmList<String> = realmListOf()
}

val RCharacter.toEntity: CharacterEntity
    get() {
        return CharacterEntity(
            id,
            name,
            status,
            species,
            type,
            gender,
            image,
            location = location?.toEntity ?: RLocation().toEntity,
            episode
        )
    }