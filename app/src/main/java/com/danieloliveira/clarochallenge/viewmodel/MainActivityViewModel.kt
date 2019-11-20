package com.danieloliveira.clarochallenge.viewmodel

import androidx.lifecycle.MutableLiveData
import com.danieloliveira.clarochallenge.enums.StringContants
import com.danieloliveira.clarochallenge.models.MovieList
import com.danieloliveira.clarochallenge.source.Repository
import kotlinx.coroutines.*

class MainActivityViewModel(private val repository: Repository): BaseViewModel() {

    val moviesData : MutableLiveData<MovieList?> = MutableLiveData()

    var page: Int? = null
    private var searchQuery: String? = null

    fun fetchData(typeSearch: StringContants? = null, query: String? = null) {
        when {
            typeSearch != null -> {
                searchQuery = null
                page = 1
                requestMovies(typeSearch)
                return
            }
            query != null -> {
                page = 1
                searchQuery = query
                searchMovies(query)
                return
            }
            searchQuery != null -> {
                searchMovies(searchQuery!!)
            }
            else -> {
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

    fun setTypeSearch(typeSearch: String) = repository.setTypeSearch(typeSearch = typeSearch)

    fun getTypeSearch(): String = repository.getTypeSearch()

    fun enableAdultContent(enable: Boolean) = repository.saveAdultContentOption(enable)

    fun isAdultContentEnabled(): Boolean = repository.getAdultContentOption()
}