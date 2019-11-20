package com.danieloliveira.clarochallenge.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.danieloliveira.clarochallenge.models.Movie

@Database(version = 1, entities = [Movie::class])
abstract class MovieDataBase: RoomDatabase() {

    abstract fun movieDao(): MovieDao

}