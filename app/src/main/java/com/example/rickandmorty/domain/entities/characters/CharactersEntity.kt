package com.example.rickandmorty.domain.entities.characters

data class CharactersEntity(
    val characters: List<CharacterEntity>,
    val hasNextPage: Boolean
)

val CharactersEntity.addNewPage: Int
    get() = if (hasNextPage) 1 else 0

data class CharactersEntityWithException<T1, T2>(val result: T1, val error: T2)
