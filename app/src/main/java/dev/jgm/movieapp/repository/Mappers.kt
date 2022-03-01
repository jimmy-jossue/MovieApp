package dev.jgm.movieapp.repository

import dev.jgm.movieapp.data.local.entities.MovieEntity
import dev.jgm.movieapp.data.network.models.MovieModel

fun MovieModel.toEntity(): MovieEntity {
    return MovieEntity(
        id = id,
        adult = adult,
        backdropPath = backdropPath,
        genreIds = genreIds,
        mediaType = mediaType,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        releaseDate = releaseDate,
        title = title,
        video = video,
        voteAverage = voteAverage,
        voteCount = voteCount,
    )
}

fun MovieEntity.toModel(): MovieModel {
    return MovieModel(
        id = id,
        adult = adult,
        backdropPath = backdropPath,
        genreIds = genreIds,
        mediaType = mediaType,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        releaseDate = releaseDate,
        title = title,
        video = video,
        voteAverage = voteAverage,
        voteCount = voteCount,
    )
}