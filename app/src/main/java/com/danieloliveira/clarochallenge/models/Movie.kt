package com.danieloliveira.clarochallenge.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MOVIES")
data class Movie(
    var adult: Boolean? = null,
    var backdrop_path: String? = null,
    @PrimaryKey
    var id: Int? = null,
    var original_language: String? = null,
    var original_title: String? = null,
    var overview: String? = null,
    var popularity: Double? = null,
    var poster_path: String? = null,
    var release_date: String? = null,
    var title: String? = null,
    var video: Boolean? = null,
    var vote_average: Double? = null,
    var vote_count: Int? = null
)