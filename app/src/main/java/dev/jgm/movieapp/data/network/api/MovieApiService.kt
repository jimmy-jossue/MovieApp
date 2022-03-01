package dev.jgm.movieapp.data.network.api

import dev.jgm.movieapp.data.network.api.Credentials.API_KEY
import dev.jgm.movieapp.data.network.models.MovieModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiService {
    @Headers("api-key:$API_KEY")
    @GET("list/1")
    suspend fun getList(
        @Query("language") language: String,
    ): Response<MovieModel>
/*
    @GET("ability/{name}")
    suspend fun getPokemonAbility(@Path("name") name: String): Response<Ability>

    @GET("pokemon-species/{name}")
    suspend fun getPokemonSpecie(@Path("name") name: String): Response<Specie>

 */
}