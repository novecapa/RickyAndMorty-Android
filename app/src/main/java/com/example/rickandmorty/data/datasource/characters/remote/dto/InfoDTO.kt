package com.example.rickandmorty.data.datasource.characters.remote.dto

data class InfoDTO(
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?
)

val InfoDTO.hasNextPage: Boolean
    get() = next?.isNotEmpty() ?: false