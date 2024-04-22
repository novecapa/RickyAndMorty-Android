package com.example.rickandmorty.ui.characterlist

import com.example.rickandmorty.mocks.datasource.CharactersDatabaseSourceTest
import com.example.rickandmorty.mocks.datasource.CharactersRemoteDataSourceTest
import com.example.rickandmorty.mocks.repository.CharactersRepositoryTest
import com.example.rickandmorty.mocks.usecase.CharactersUseCaseTest
import io.mockk.coVerify
import org.junit.Test

class CharacterListViewModelTest {

    private val useCase: CharactersUseCaseTest

    init {
        val remoteDatasource = CharactersRemoteDataSourceTest()
//        val realm = RealmHelper.realm
//        val databaseSource = CharactersDatabaseSource(realm)
        val databaseSource = CharactersDatabaseSourceTest()
        val repository = CharactersRepositoryTest(remoteDatasource, databaseSource)
        useCase = CharactersUseCaseTest(repository)
    }

    @Test
    fun test_fetchData() {
        // Given

        // When

        // Then
        coVerify {
            val data = useCase.getCharacters(1)
            println(data.result.characters.count())
        }
    }
}