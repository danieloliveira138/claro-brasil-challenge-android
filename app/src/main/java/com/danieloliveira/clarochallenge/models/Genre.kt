package com.danieloliveira.clarochallenge.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "GENRES")
data class Genre(
    @PrimaryKey
    var id: Int? = null,
    var name: String? = ""
)