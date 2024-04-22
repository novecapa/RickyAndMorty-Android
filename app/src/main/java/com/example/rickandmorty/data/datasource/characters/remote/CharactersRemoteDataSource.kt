package com.example.rickandmorty.data.datasource.characters.remote.dto

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit

class CharactersRemoteDataSource(
    private val retrofit: Retrofit
) {

    suspend fun getCharacters(page: Int): CharactersDTO? {
        return withContext(Dispatchers.IO) {
            retrofit.create(CharactersRemoteDataSourceInterface::class.java)
                .getCharacters(page).body()
        }
    }

    suspend fun searchCharacters(page: Int,
                                 name: String): CharactersDTO? {
        return withContext(Dispatchers.IO) {
            retrofit.create(CharactersRemoteDataSourceInterface::class.java)
                .searchCharacters(page, name).body()
        }
    }
}