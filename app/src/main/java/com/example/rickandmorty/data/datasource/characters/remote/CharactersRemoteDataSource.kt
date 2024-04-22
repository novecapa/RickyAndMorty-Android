package com.example.rickandmorty.data.datasource.characters.remote.dto

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import java.lang.Exception

interface CharactersRemoteDataSourceInterface {
    suspend fun getCharacters(page: Int): CharactersWithException<CharactersDTO?, Exception?>
    suspend fun searchCharacters(page: Int, name: String): CharactersWithException<CharactersDTO?, Exception?>
}

class CharactersRemoteDataSource(
    private val retrofit: Retrofit
): CharactersRemoteDataSourceInterface {

    override suspend fun getCharacters(page: Int): CharactersWithException<CharactersDTO?, Exception?> {
        return withContext(Dispatchers.IO) {
            try {
                val res =  retrofit.create(CharactersRemoteInterface::class.java).getCharacters(page).body()
                CharactersWithException(res, null)
            } catch (e: Exception) {
                CharactersWithException(null, e)
            }
        }
    }

    override suspend fun searchCharacters(page: Int,
                                          name: String): CharactersWithException<CharactersDTO?, Exception?> {
        return withContext(Dispatchers.IO) {
            try {
                val res = retrofit.create(CharactersRemoteInterface::class.java).searchCharacters(page, name).body()
                CharactersWithException(res, null)
            } catch (e: Exception) {
                CharactersWithException(null, e)
            }
        }
    }
}