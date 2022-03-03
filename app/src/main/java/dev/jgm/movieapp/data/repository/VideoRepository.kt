package dev.jgm.movieapp.data.repository

import dev.jgm.movieapp.core.Response
import dev.jgm.movieapp.data.network.api.MovieApiService
import dev.jgm.movieapp.data.network.models.MovieVideosResponse
import dev.jgm.movieapp.domain.model.Video
import javax.inject.Inject

class VideoRepository @Inject constructor(
    private val api: MovieApiService
) {
    suspend fun getMovieVideo(movieId: Int, language: String): Response<List<Video>> {
        //return try {
        val call = api.getMovieVideos(movieId, language)
        val response: MovieVideosResponse? = call.body()
        return if (response != null && !response.videos.isNullOrEmpty()) {
            val videos = response.videos.map { it.toVideo() }
            Response.Success(videos)
        } else {
            Response.Success(emptyList())
        }
        //} catch (ex: Exception) {
        //    Response.Failure(ex)
        //}
    }
}