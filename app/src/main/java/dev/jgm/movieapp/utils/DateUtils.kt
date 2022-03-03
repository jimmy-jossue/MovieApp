package dev.jgm.movieapp.utils

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {
    private fun toDate(string: String, locale: Locale): Date? {
        return SimpleDateFormat("yyyy-MM-dd", locale).parse(string)
    }

    fun getYear(string: String, locale: Locale): String {
        if (string.isEmpty()) return ""

        return try {
            val date = toDate(string, locale)
            if (date != null) {
                SimpleDateFormat("dd MMMM yyyy", locale).format(date)
            } else {
                ""
            }
        } catch (e: Exception) {
            ""
        }
    }
}