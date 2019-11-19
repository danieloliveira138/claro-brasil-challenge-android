package com.danieloliveira.clarochallenge.source

import com.danieloliveira.clarochallenge.models.MovieDetail
import com.danieloliveira.clarochallenge.models.MovieList

interface Repository {
    suspend fun requestMovies(typeSearch: String,
                              api_key: String,
                              language: String,
                              adultContent: Boolean,
                              page: Int): MovieList

    suspend fun requestMovie(id: Int,
                             api_key: String,
                             language: String): MovieDetail

    suspend fun searchMovie(api_key: String,
                            language: String,
                            adultContent: Boolean,
                            query: String,
                            page: Int): MovieList


}