package com.example.rickandmorty.domain.entities.characters

import com.example.rickandmorty.data.datasource.characters.database.models.RCharacter

data class CharacterEntity(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val image: String,
    val location: LocationEntity,
    val episode: List<String>
)