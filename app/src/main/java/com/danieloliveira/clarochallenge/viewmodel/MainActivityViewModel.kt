package com.danieloliveira.clarochallenge.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.danieloliveira.clarochallenge.enums.StringContants
import com.danieloliveira.clarochallenge.models.Movie
import com.danieloliveira.clarochallenge.models.MovieDetail
import com.danieloliveira.clarochallenge.models.MovieList
import com.danieloliveira.clarochallenge.source.Repository
import kotlinx.coroutines.*

class MainActivityViewModel(private val repository: Repository): BaseViewModel() {

    val moviesData : MutableLiveData<MovieList?> = MutableLiveData()
    var flagFavorite = false
    var page: Int? = null
    private var searchQuery: String? = null

    fun fetchData(typeSearch: StringContants? = null, query: String? = null) {
        when {
            typeSearch != null -> {
                searchQuery = null
                flagFavorite = false
                searchQuery = null
                page = 1
                requestMovies(typeSearch)
                return
            }
            query != null -> {
                flagFavorite = false
                page = 1
                searchQuery = query
                searchMovies(query)
                return
            }
            searchQuery != null -> {
                flagFavorite = false
                searchMovies(searchQuery!!)
            }
            else -> {
                if (flagFavorite) return

                requestMovies(null)

            }
        }
        
    }

    private fun requestMovies(typeSearch: StringContants?) {

        if (typeSearch != null) setTypeSearch(typeSearch.const)

        scope.launch {
            moviesData.postValue (
                repository.requestMovies(page = page ?: 1))
        }
    }

    private fun searchMovies(query: String) {

        scope.launch {
            moviesData.postValue(
                repository.searchMovie(query, page?: 1)
            )
        }
    }

    fun requestFavoriteMovies() {
        flagFavorite = true
        searchQuery = null
        var savedMovies: List<Movie>? = null

        scope.launch {
             savedMovies = repository.getFavoriteMovies()
            if(savedMovies == null) {
                moviesData.postValue(null)
                return@launch
            }
            moviesData.postValue(
                MovieList(page = 1, results = savedMovies)
            )
        }

    }

    fun setTypeSearch(typeSearch: String) = repository.setTypeSearch(typeSearch = typeSearch)

    fun getTypeSearch(): String = repository.getTypeSearch()

    fun enableAdultContent(enable: Boolean) = repository.saveAdultContentOption(enable)

    fun isAdultContentEnabled(): Boolean = repository.getAdultContentOption()
}