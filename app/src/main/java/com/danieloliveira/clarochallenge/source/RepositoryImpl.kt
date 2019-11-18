package com.danieloliveira.clarochallenge.source

import com.danieloliveira.clarochallenge.source.remote.MovieService

class RepositoryImpl(private val service: MovieService): Repository {
    override fun fetchData() {

    }

}
