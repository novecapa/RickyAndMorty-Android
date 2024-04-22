package com.example.rickandmorty.data.datasource.characters.database.models

import com.example.rickandmorty.domain.entities.characters.LocationEntity
import io.realm.kotlin.types.RealmObject

class RLocation: RealmObject {
    var name: String = ""
    var url: String = ""
}

val RLocation.toEntity: LocationEntity
    get() {
        return LocationEntity(
            name,
            url
        )
    }