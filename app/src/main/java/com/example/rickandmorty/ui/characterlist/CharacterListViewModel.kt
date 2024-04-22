package com.example.rickandmorty.ui.characterlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.data.datasource.characters.RetrofitHelper
import com.example.rickandmorty.data.datasource.characters.remote.dto.CharactersRemoteDataSource
import com.example.rickandmorty.data.repositories.characters.CharactersRepository
import com.example.rickandmorty.domain.entities.characters.CharactersEntity
import com.example.rickandmorty.domain.entities.characters.addNewPage
import com.example.rickandmorty.domain.usecases.characters.CharactersUseCase
import kotlinx.coroutines.launch

class CharacterListViewModel: ViewModel() {

    private val useCase: CharactersUseCase
    private var isLoading = false
    private var currentPage = 1
    private var hasNewPage = true

    init {
        val retrofit = RetrofitHelper.getRetrofit()
        val dataSource = CharactersRemoteDataSource(retrofit)
        val repository = CharactersRepository(dataSource)
        useCase = CharactersUseCase(repository)
    }

    fun fetchCharacters() {
        if (!canLoadNewPage) return
        viewModelScope.launch {
            isLoading = true
            // showIndicator.postValue(true)
            val result = useCase.getCharacters(currentPage)
            // showIndicator.postValue(true)
            println(result.characters.count())
            isLoading = false
        }
    }

    private fun resetPagination() {
        currentPage = 1
        hasNewPage = true
        // scrollToTop = true
    }

    val canLoadNewPage: Boolean
        get() = hasNewPage && !isLoading

    private fun populateResult(result: CharactersEntity) {
        if (currentPage == 1) {
//            characters.removeAll()
//            characters.append(contentsOf: result.characters)
        } else {
//            characters.append(contentsOf: result.characters)
        }
        currentPage += result.addNewPage
        hasNewPage = result.hasNextPage
    }
}