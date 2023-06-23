package com.example.netflixreplicated.data.repositories

import com.example.netflixreplicated.data.models.Film

interface FilmRepositoryImpl {
    suspend fun getRecentlyAddedList(): List<Film>
    suspend fun getTopTenList(): List<Film>
}