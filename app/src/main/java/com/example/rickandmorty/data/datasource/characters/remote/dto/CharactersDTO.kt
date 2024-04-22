package com.example.rickandmorty.data.datasource.characters.remote.dto

import com.example.rickandmorty.domain.entities.characters.CharactersEntity

data class CharactersDTO(
    val info: InfoDTO,
    val results: List<CharacterDTO>
)

val CharactersDTO.toEntity: CharactersEntity
    get() = CharactersEntity(
        characters = results.map { it.toEntity },
        hasNextPage = info.hasNextPage
    )