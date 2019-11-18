package com.danieloliveira.clarochallenge.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "LANGUAGES")
data class Language(
    var english_name: String = "",
    @PrimaryKey
    var iso_639_1: String = "",
    var name: String = ""
)