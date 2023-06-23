package com.example.netflixreplicated.data.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.netflixreplicated.data.models.Film
import com.example.netflixreplicated.data.repositories.FilmRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    private val repository: FilmRepository = FilmRepository()

    init {
        getRecentlyAddedList()
        getTopTenList()
    }

    private fun getRecentlyAddedList() {
        viewModelScope.launch {
            val list = repository.getRecentlyAddedList()
            _uiState.update { it.copy(recentlyAddedList = list) }
        }
    }

    private fun getTopTenList() {
        viewModelScope.launch {
            val list = repository.getTopTenList()
            _uiState.update { it.copy(topTenList = list) }
        }
    }

    data class UiState(
        val recentlyAddedList: List<Film> = emptyList(),
        val topTenList: List<Film> = emptyList()
    )
}