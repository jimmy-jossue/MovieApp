package dev.jgm.movieapp.data.network.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class VideoModel(
    @Expose @SerializedName("id") val id: String,
    @Expose @SerializedName("key") val key: String,
    @Expose @SerializedName("name") val name: String,
    @Expose @SerializedName("site") val site: String,
    @Expose @SerializedName("size") val size: Int,
    @Expose @SerializedName("type") val type: String,
)