package com.danieloliveira.clarochallenge.utils

import android.util.Log
import com.danieloliveira.clarochallenge.models.Movie
import com.danieloliveira.clarochallenge.models.MovieDetail


fun String.logIt(){
    Log.d("LOGGER", this)
}

fun MovieDetail.toMovie(): Movie =
    Movie(id = this.id,
        adult = this.adult,
        backdrop_path = this.backdrop_path,
        original_language = this.original_language,
        overview = this.overview,
        original_title = this.original_title,
        popularity = this.popularity,
        poster_path = this.poster_path,
        release_date = this.release_date,
        title = this.title,
        video = this.video,
        vote_average = this.vote_average,
        vote_count = this.vote_count)

