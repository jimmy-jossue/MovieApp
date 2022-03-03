package dev.jgm.movieapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.jgm.movieapp.data.local.dao.MovieDao
import dev.jgm.movieapp.data.local.entities.MovieEntity

@Database(entities = [MovieEntity::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun getMovieDao(): MovieDao
}