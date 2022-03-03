package dev.jgm.movieapp.domain.usecases

import dev.jgm.movieapp.core.Response
import dev.jgm.movieapp.data.repository.MovieRepository
import dev.jgm.movieapp.domain.model.Movie
import dev.jgm.movieapp.domain.model.MovieType
import javax.inject.Inject

class GetAllMovies @Inject constructor(private val repository: MovieRepository) {
    suspend operator fun invoke(
        movieType: MovieType,
        language: String,
        page: Int
    ): Response<List<Movie>> {
        return repository.getMovies(movieType, language, page)
    }
}