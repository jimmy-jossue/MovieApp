package dev.jgm.movieapp.domain.model

data class Movie(
    val id: Int,
    val adult: Boolean,
    val backdropPath: String,
    val genreIds: List<Int>,
    val mediaType: String,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int
)