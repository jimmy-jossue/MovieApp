package dev.jgm.movieapp.data.network.api

import dev.jgm.movieapp.data.network.models.MovieResponse
import dev.jgm.movieapp.data.network.models.MovieVideosResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiService {

    @GET("movie/popular")
    suspend fun getMoviesPopular(
        @Query("language") language: String,
        @Query("page") page: Int
    ): Response<MovieResponse>

    @GET("movie/now_playing")
    suspend fun getMoviesNowPlaying(
        @Query("language") language: String,
        @Query("page") page: Int
    ): Response<MovieResponse>

    @GET("movie/{id}/videos")
    suspend fun getMovieVideos(
        @Path("id") id: Int,
        @Query("language") language: String,
    ): Response<MovieVideosResponse>
}