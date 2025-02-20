package com.ahmed.habib.utils.list_of_timers

data class MovieModel(
    val name: String,
    val description: String,
    val date: String,
    val time: String
)

val movies = listOf(
    MovieModel(
        "Movie One",
        "Description for Movie One",
        "25-2-2025",
        "10:30 pm"
    ),
    MovieModel(
        "Movie Two",
        "Description for Movie Two",
        "26-2-2025",
        "10:30 pm"
    ),
    MovieModel(
        "Movie Three",
        "Description for Movie Three",
        "27-2-2025",
        "10:30 pm"
    )
)