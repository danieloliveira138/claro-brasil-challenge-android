package com.danieloliveira.clarochallenge.source

import com.danieloliveira.clarochallenge.BuildConfig
import com.danieloliveira.clarochallenge.enums.Languages
import com.danieloliveira.clarochallenge.models.MovieDetail
import com.danieloliveira.clarochallenge.models.MovieList
import com.danieloliveira.clarochallenge.models.Video
import com.danieloliveira.clarochallenge.models.VideoResponse
import com.danieloliveira.clarochallenge.source.prefs.SharedPrefs
import com.danieloliveira.clarochallenge.source.remote.MovieService

class RepositoryImpl(private val service: MovieService,
                     private val sharedPrefs: SharedPrefs): Repository {

    override suspend fun requestMovies(page: Int): MovieList? =
            service.getMovies(
                typeSearch = getTypeSearch(),
                api_key = BuildConfig.TOKEN_ID,
                language = getLanguage(),
                adultContent = getAdultContentOption(),
                page = page)

    override suspend fun requestMovie(id: Int): MovieDetail? =
            service.getMovie(
                id = id,
                api_key = BuildConfig.TOKEN_ID,
                language = getLanguage())

    override suspend fun searchMovie(query: String, page: Int): MovieList? =
            service.searchMovie(
                api_key = BuildConfig.TOKEN_ID,
                language = getLanguage(),
                adultContent = getAdultContentOption(),
                query = query,
                page = page)

    override suspend fun requestVideos(id: Int): VideoResponse? = service.getVideos(id = id)

    override fun saveLanguage(languages: Languages) = sharedPrefs.setLanguage(languages)

    override fun getLanguage(): String = sharedPrefs.getLanguage()

    override fun saveAdultContentOption(enable: Boolean) = sharedPrefs.setAdultContent(enable)

    override fun getAdultContentOption(): Boolean = sharedPrefs.getAdultContent()

    override fun setTypeSearch(typeSearch: String) = sharedPrefs.setTypeSearch(typeSearch)

    override fun getTypeSearch(): String = sharedPrefs.getTypeSearch()

}
