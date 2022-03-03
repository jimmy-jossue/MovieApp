package dev.jgm.movieapp.data.network.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MovieVideosResponse(
    @Expose @SerializedName("id") val id: Int,
    @Expose @SerializedName("results") val videos: ArrayList<VideoModel>
)