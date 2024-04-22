package com.example.rickandmorty.data.datasource.characters.remote.dto

import com.example.rickandmorty.domain.entities.characters.CharacterEntity

data class CharacterDTO(
    val created: String,
    val episode: List<String>,
    val gender: String,
    val id: Int,
    val image: String,
    val location: LocationDTO,
    val name: String,
    val species: String,
    val status: String,
    val type: String,
    val url: String
)

val CharacterDTO.toEntity: CharacterEntity
    get() = CharacterEntity(
        id,
        name,
        status,
        species,
        type,
        gender,
        image,
        location.toEntity,
        episode,
    )