package com.danieloliveira.clarochallenge.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "COUNTRIES")
data class Country(
    val english_name: String = "",
    @PrimaryKey
    val iso_3166_1: String = ""
)