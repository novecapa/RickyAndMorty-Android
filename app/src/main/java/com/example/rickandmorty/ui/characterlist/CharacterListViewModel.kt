package com.example.rickandmorty.ui.characterlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.data.datasource.characters.database.models.CharactersDatabaseSource
import com.example.rickandmorty.manager.network.RetrofitHelper
import com.example.rickandmorty.data.datasource.characters.remote.dto.CharactersRemoteDataSource
import com.example.rickandmorty.data.repositories.characters.CharactersRepository
import com.example.rickandmorty.domain.entities.characters.CharacterEntity
import com.example.rickandmorty.domain.entities.characters.addNewPage
import com.example.rickandmorty.domain.usecases.characters.CharactersUseCase
import com.example.rickandmorty.manager.database.RealmHelper
import kotlinx.coroutines.launch

class CharacterListViewModel: ViewModel() {

    private val useCase: CharactersUseCase
    private var currentPage = 1
    private var hasNewPage = true

    var characterList: ArrayList<CharacterEntity> = arrayListOf()

    val refreshList = MutableLiveData<Boolean>()
    val scrollToTop = MutableLiveData<Boolean>()
    val showIndicator = MutableLiveData<Boolean>()

    init {
        val retrofit = RetrofitHelper.getRetrofit()
        val dataSource = CharactersRemoteDataSource(retrofit)
        val realm = RealmHelper.realm
        val databaseSource = CharactersDatabaseSource(realm)
        val repository = CharactersRepository(dataSource, databaseSource)
        useCase = CharactersUseCase(repository)
    }

    fun fetchCharacters() {
        if (!canLoadNewPage) return
        viewModelScope.launch {
            showIndicator.postValue(true)

            val result = useCase.getCharacters(currentPage)
            if (currentPage == 1) { characterList.clear() }

            characterList.addAll(result.characters)

            currentPage += result.addNewPage
            hasNewPage = result.hasNextPage

            refreshList.postValue(true)
            showIndicator.postValue(false)
        }
    }

    private fun resetPagination() {
        currentPage = 1
        hasNewPage = true
        scrollToTop.postValue(true)
    }

    val canLoadNewPage: Boolean
        get() = hasNewPage
}