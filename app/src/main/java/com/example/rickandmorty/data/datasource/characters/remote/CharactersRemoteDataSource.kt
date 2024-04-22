package com.example.rickandmorty.data.datasource.characters.remote.dto

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import java.lang.Exception

interface CharactersRemoteDataSourceInterface {
    suspend fun getCharacters(page: Int): CharactersDTO?
    suspend fun searchCharacters(page: Int, name: String): CharactersDTO?
}

class CharactersRemoteDataSource(
    private val retrofit: Retrofit
): CharactersRemoteDataSourceInterface {

    override suspend fun getCharacters(page: Int): CharactersDTO? {
        return withContext(Dispatchers.IO) {
            try {
                retrofit.create(CharactersRemoteInterface::class.java)
                    .getCharacters(page).body()
            } catch (e: Exception) {
                null
            }
        }
    }

    override suspend fun searchCharacters(page: Int,
                                 name: String): CharactersDTO? {
        return withContext(Dispatchers.IO) {
            try {
            retrofit.create(CharactersRemoteInterface::class.java)
                .searchCharacters(page, name).body()
            } catch (e: Exception) {
                null
            }
        }
    }
}