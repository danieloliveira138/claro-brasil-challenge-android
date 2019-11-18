package com.danieloliveira.clarochallenge.models

data class MovieList(
    var page: Int? = null,
    var results: ArrayList<Movie>? = arrayListOf(),
    var total_pages: Int? = null,
    var total_results: Int? = null
)