package com.danieloliveira.clarochallenge.source

import com.danieloliveira.clarochallenge.enums.Languages
import com.danieloliveira.clarochallenge.models.Movie
import com.danieloliveira.clarochallenge.models.MovieDetail
import com.danieloliveira.clarochallenge.models.MovieList
import com.danieloliveira.clarochallenge.models.VideoResponse

interface Repository {

    suspend fun requestMovies(page: Int): MovieList?

    suspend fun requestMovie(id: Int): MovieDetail?

    suspend fun searchMovie(query: String, page: Int): MovieList?

    suspend fun requestVideos(id: Int): VideoResponse?

    fun saveLanguage(languages: Languages)

    fun getLanguage(): String

    fun setTypeSearch(typeSearch: String)

    fun getTypeSearch(): String

    fun saveAdultContentOption(enable: Boolean)

    fun getAdultContentOption(): Boolean

    suspend fun getFavoriteMovies(): List<Movie>?

    suspend fun getFavoriteMovie(id: Int): Movie?

    suspend fun setFavoriteMovie(movie: Movie)

    suspend fun deleteMovie(movie: Movie)

}