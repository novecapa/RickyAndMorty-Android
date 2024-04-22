package com.example.rickandmorty.data.datasource.characters.remote.dto

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CharactersRemoteInterface {

    @GET("character")
    suspend fun getCharacters(@Query("page") page: Int): Response<CharactersDTO?>

    @GET("character")
    suspend fun searchCharacters(@Query("page") page: Int,
                                 @Query("name") name: String): Response<CharactersDTO?>
}