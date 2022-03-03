package dev.jgm.movieapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.jgm.movieapp.data.local.entities.MovieEntity

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie_table WHERE type = (:movieType) ORDER BY popularity DESC")
    suspend fun getAllMovies(movieType: Int): List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(quotes: List<MovieEntity>)

    @Query("DELETE FROM movie_table")
    fun deleteAllMovies()
}