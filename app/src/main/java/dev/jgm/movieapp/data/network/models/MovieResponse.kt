package dev.jgm.movieapp.data.network.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @Expose @SerializedName("page") val page: Int,
    @Expose @SerializedName("results") val results: List<MovieModel>,
    @Expose @SerializedName("total_pages") val totalPages: Int,
    @Expose @SerializedName("total_results") val totalResults: Int
)
