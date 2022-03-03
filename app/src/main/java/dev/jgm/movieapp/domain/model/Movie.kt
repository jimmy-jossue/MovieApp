package dev.jgm.movieapp.domain.model

import android.content.Context
import dev.jgm.movieapp.data.network.api.Credentials.IMAGE_URL
import dev.jgm.movieapp.utils.DateUtils
import java.io.Serializable
import java.util.*

data class Movie(
    val id: Int,
    val adult: Boolean,
    val backdropPath: String,
    val genreIds: List<Int>,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int,
    var type: MovieType? = null
) : Serializable {
    fun getGenres(context: Context): String {
        val genres = genreIds.map { MovieGenre.get(it) }

        return genres.joinToString(separator = ", ") {
            if (it != null) {
                context.getString(it.genre)
            } else {
                ""
            }
        }
    }

    fun getImageUrl(path: String?, size: String = "w500/"): String {
        if (path == null || path.isEmpty()) return ""
        return "${IMAGE_URL}$size$path"
    }

    fun getDate(locale: Locale) = DateUtils.getYear(releaseDate, locale)
}
