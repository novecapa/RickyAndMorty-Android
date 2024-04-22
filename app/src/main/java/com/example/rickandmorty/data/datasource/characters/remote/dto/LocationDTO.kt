package com.example.rickandmorty.data.datasource.characters.remote.dto

import com.example.rickandmorty.domain.entities.characters.LocationEntity

data class LocationDTO(
    val name: String,
    val url: String
)

val LocationDTO.toEntity: LocationEntity
    get() = LocationEntity(name, url)