package dev.jgm.movieapp.domain.model

data class Video(
    val id: String,
    val key: String,
    val name: String,
    val site: String,
    val size: Int,
    val type: String,
)
