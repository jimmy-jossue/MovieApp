package dev.jgm.movieapp.domain.model

import androidx.annotation.StringRes
import dev.jgm.movieapp.R

enum class MovieGenre(val id: Int, @StringRes val genre: Int) {
    ACTION(id = 28, genre = R.string.action),
    ADVENTURE(id = 12, genre = R.string.adventure),
    ANIMATION(id = 16, genre = R.string.animation),
    COMEDY(id = 35, genre = R.string.comedy),
    CRIME(id = 80, genre = R.string.crime),
    DOCUMENTARY(id = 99, genre = R.string.documentary),
    DRAMA(id = 18, genre = R.string.drama),
    FAMILY(id = 10751, genre = R.string.family),
    FANTASY(id = 14, genre = R.string.fantasy),
    HISTORY(id = 36, genre = R.string.history),
    HORROR(id = 27, genre = R.string.horror),
    MUSIC(id = 10402, genre = R.string.music),
    MYSTERY(id = 9648, genre = R.string.mystery),
    ROMANCE(id = 10749, genre = R.string.romance),
    SCIENCE_FICTION(id = 878, genre = R.string.science_fiction),
    TV_MOVIE(id = 10770, genre = R.string.tv_movie),
    THRILLER(id = 53, genre = R.string.thriller),
    WAR(id = 10752, genre = R.string.war),
    WESTERN(id = 37, genre = R.string.western);

    companion object {
        fun get(value: Int) = MovieGenre.values().find { it.id == value }
    }

}