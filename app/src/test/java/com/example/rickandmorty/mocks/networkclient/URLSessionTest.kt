package com.example.rickandmorty.mocks.networkclient

import com.example.rickandmorty.data.datasource.characters.remote.dto.CharactersDTO
import com.example.rickandmorty.data.datasource.characters.remote.dto.CharactersRemoteDataSourceInterface
import com.example.rickandmorty.data.datasource.characters.remote.dto.CharactersWithException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.lang.Exception

class URLSessionTest: CharactersRemoteDataSourceInterface {
    override suspend fun getCharacters(page: Int): CharactersWithException<CharactersDTO?, Exception?> {
        return withContext(Dispatchers.IO) {
            val archivo = File("ruta")
            val lector = BufferedReader(FileReader(archivo))
            val contenido = StringBuilder()
            var linea: String?
            while (lector.readLine().also { linea = it } != null) {
                contenido.append(linea).append("\n")
            }
            lector.close()
            CharactersWithException(null, null)
        }
    }

    override suspend fun searchCharacters(
        page: Int,
        name: String
    ): CharactersWithException<CharactersDTO?, Exception?> {
        TODO("Not yet implemented")
    }
}