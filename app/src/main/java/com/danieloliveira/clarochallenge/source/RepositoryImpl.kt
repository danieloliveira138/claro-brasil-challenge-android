package com.danieloliveira.clarochallenge.source

import com.danieloliveira.clarochallenge.models.MovieDetail
import com.danieloliveira.clarochallenge.models.MovieList
import com.danieloliveira.clarochallenge.source.remote.MovieService

class RepositoryImpl(private val service: MovieService): Repository {
    override suspend fun requestMovies(
        typeSearch: String,
        api_key: String,
        language: String,
        adultContent: Boolean,
        page: Int): MovieList =
            service.getMovies(
                typeSearch = typeSearch,
                api_key = api_key,
                language = language,
                adultContent = adultContent,
                page = page)

    override suspend fun requestMovie(
        id: Int,
        api_key: String,
        language: String): MovieDetail =
            service.getMovie(
                id = id,
                api_key = api_key,
                language = language)

    override suspend fun searchMovie(
        api_key: String,
        language: String,
        adultContent: Boolean,
        query: String,
        page: Int): MovieList =
            service.searchMovie(
                api_key = api_key,
                language = language,
                adultContent = adultContent,
                query = query,
                page = page)
}
