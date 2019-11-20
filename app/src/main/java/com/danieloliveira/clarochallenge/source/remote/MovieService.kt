package com.danieloliveira.clarochallenge.source.remote

import com.danieloliveira.clarochallenge.BuildConfig
import com.danieloliveira.clarochallenge.models.Country
import com.danieloliveira.clarochallenge.models.Language
import com.danieloliveira.clarochallenge.models.MovieDetail
import com.danieloliveira.clarochallenge.models.MovieList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    @GET("movie/{id}")
    suspend fun getMovie(
        @Path("id") id: Int,
        @Query("api_key") api_key: String = BuildConfig.TOKEN_ID,
        @Query("language") language: String = "pt-BR"): MovieDetail?

    @GET("movie/{param}")
    suspend fun getMovies(
        @Path("param") typeSearch: String,
        @Query("api_key") api_key: String = BuildConfig.TOKEN_ID,
        @Query("language") language: String,
        @Query("include_adult") adultContent: Boolean,
        @Query("page") page: Int = 1): MovieList?

    @GET("search/movie")
    suspend fun searchMovie(
        @Query("api_key") api_key: String = BuildConfig.TOKEN_ID,
        @Query("language") language: String,
        @Query("include_adult") adultContent: Boolean,
        @Query("query") query: String,
        @Query("page") page: Int = 1): MovieList?

    @GET("configuration/languages")
    suspend fun getLanguages(@Query("api_key") api_key: String): ArrayList<Language>?

    @GET("configuration/countries")
    fun getCountries(@Query("api_key") api_key: String): ArrayList<Country>?
}