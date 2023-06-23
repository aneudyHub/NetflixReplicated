package com.example.netflixreplicated.data.models

data class Film(
    val title: String,
    val image: String,
    val isTopTen: Boolean,
    val category: FilmCategory
)


enum class FilmCategory{
    DRAMA,
    ROMANCE,
    ACTION,
    COMEDY,
    HORROR,
    INTERNATIONAL
}