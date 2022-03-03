package dev.jgm.movieapp.domain.model

enum class MovieType(val type: Int) {
    POPULAR(type = 1),
    PLAYING_NOW(type = 2);

    companion object {
        fun get(value: Int) = values().find { it.type == value }
    }
}