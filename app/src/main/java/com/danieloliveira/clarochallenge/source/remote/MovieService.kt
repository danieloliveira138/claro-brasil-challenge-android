package com.danieloliveira.clarochallenge.source.remote

import androidx.lifecycle.LiveData
import com.danieloliveira.clarochallenge.models.Country
import com.danieloliveira.clarochallenge.models.Language
import com.danieloliveira.clarochallenge.models.MovieDetail
import com.danieloliveira.clarochallenge.models.MovieList
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {
    @GET("movie/{id}")
    fun getMovie(
        @Path("id") id: Int,
        @Query("api_key") api_key: String,
        @Query("language") language: String = "pt-BR"): Deferred<MovieDetail>

    @GET("movie/{param}")
    fun getMovies(
        @Path("param") typeSearch: String,
        @Query("api_key") api_key: String,
        @Query("language") language: String,
        @Query("include_adult") adultContent: Boolean,
        @Query("page") page: Int): Deferred<MovieList>

    @GET("search/movie")
    fun searchMovie(
        @Query("api_key") api_key: String,
        @Query("language") language: String,
        @Query("include_adult") adultContent: Boolean,
        @Query("query") query: String,
        @Query("page") page: Int): Deferred<MovieList>

    @GET("configuration/languages")
    fun getLanguages(@Query("api_key") api_key: String): Deferred<ArrayList<Language>>

    @GET("configuration/countries")
    fun getCountries(@Query("api_key") api_key: String): Deferred<ArrayList<Country>>
}