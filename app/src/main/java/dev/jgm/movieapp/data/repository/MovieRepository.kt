package dev.jgm.movieapp.data.repository

import dev.jgm.movieapp.core.Response
import dev.jgm.movieapp.data.local.dao.MovieDao
import dev.jgm.movieapp.data.network.api.MovieApiService
import dev.jgm.movieapp.data.network.models.MovieResponse
import dev.jgm.movieapp.domain.model.Movie
import dev.jgm.movieapp.domain.model.MovieType
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val api: MovieApiService,
    private val dao: MovieDao
) {
    private suspend fun getMoviesFromApi(
        type: MovieType,
        language: String,
        page: Int
    ): List<Movie> {
        return try {
            val call = when (type) {
                MovieType.POPULAR -> api.getMoviesPopular(language, page)
                MovieType.PLAYING_NOW -> api.getMoviesNowPlaying(language, page)
            }
            val response: MovieResponse? = call.body()
            response?.results?.map { it.toMovie().copy(type = type) } ?: emptyList()
        } catch (ex: Exception) {
            emptyList()
        }
    }

    private suspend fun getMoviesFromDatabase(movieType: MovieType): List<Movie> {
        return try {
            dao.getAllMovies(movieType.type).map { entity ->
                entity.toMovie().copy(type = MovieType.get(entity.type))
            }
        } catch (ex: Exception) {
            throw ex
        }
    }

    private suspend fun insertMovies(movies: List<Movie>) {
        try {
            val moviesEntities = movies.map { it.toMovieEntity().copy(type = it.type?.type ?: -1) }
            dao.insertAll(moviesEntities)
        } catch (ex: Exception) {
            throw ex
        }
    }

    suspend fun getMovies(
        movieType: MovieType,
        language: String,
        page: Int
    ): Response<List<Movie>> {
        return try {
            val movies = getMoviesFromApi(movieType, language, page)
            if (movies.isNotEmpty()) {
                insertMovies(movies)
                Response.Success(movies)
            } else {
                Response.Success(getMoviesFromDatabase(movieType))
            }
        } catch (ex: Exception) {
            Response.Failure(ex)
        }
    }
}
