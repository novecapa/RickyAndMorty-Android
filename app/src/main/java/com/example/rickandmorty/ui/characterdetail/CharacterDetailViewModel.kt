package com.example.rickandmorty.ui.characterdetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickandmorty.data.datasource.characters.database.models.CharactersDatabaseSource
import com.example.rickandmorty.data.datasource.characters.remote.dto.CharactersRemoteDataSource
import com.example.rickandmorty.data.repositories.characters.CharactersRepository
import com.example.rickandmorty.domain.entities.characters.CharacterEntity
import com.example.rickandmorty.domain.usecases.characters.CharactersUseCase
import com.example.rickandmorty.manager.database.RealmHelper
import com.example.rickandmorty.manager.network.NetworkMonitor
import com.example.rickandmorty.manager.network.RetrofitHelper

class CharacterDetailViewModel(val character: CharacterEntity): ViewModel() {

    private val useCase: CharactersUseCase

    val refreshRelated = MutableLiveData<Boolean>()

    init {
        val retrofit = RetrofitHelper.getRetrofit()
        val dataSource = CharactersRemoteDataSource(retrofit)
        val realm = RealmHelper.realm
        val databaseSource = CharactersDatabaseSource(realm)
        val networkMonitor = NetworkMonitor()
        val repository = CharactersRepository(dataSource, databaseSource, networkMonitor)
        useCase = CharactersUseCase(repository)
    }

    val characterDetail: CharacterEntity
        get() = character

    fun fetchRelated() {}
}