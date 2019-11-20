package com.danieloliveira.clarochallenge.source.local

import androidx.room.*
import com.danieloliveira.clarochallenge.BuildConfig
import com.danieloliveira.clarochallenge.models.Movie

@Dao
interface MovieDao {
    @Query("SELECT * FROM ${BuildConfig.MOVIE_TABLE} WHERE id = :id")
    suspend fun getMovie(id: Int): Movie

    @Query("SELECT * FROM ${BuildConfig.MOVIE_TABLE} ")
    suspend fun getMovies(): List<Movie>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: Movie)

    @Delete
    suspend fun deleteMovie(movie: Movie)
}