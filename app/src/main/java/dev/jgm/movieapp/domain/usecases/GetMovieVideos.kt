package dev.jgm.movieapp.domain.usecases

import dev.jgm.movieapp.core.Response
import dev.jgm.movieapp.data.repository.VideoRepository
import dev.jgm.movieapp.domain.model.Video
import javax.inject.Inject

class GetMovieVideos @Inject constructor(private val repository: VideoRepository) {
    suspend operator fun invoke(movieId: Int, language: String): Response<List<Video>> {
        return repository.getMovieVideo(movieId, language)
    }
}