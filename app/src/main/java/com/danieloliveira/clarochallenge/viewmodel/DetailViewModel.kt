package com.danieloliveira.clarochallenge.viewmodel

import androidx.lifecycle.MutableLiveData
import com.danieloliveira.clarochallenge.models.MovieDetail
import com.danieloliveira.clarochallenge.source.Repository
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: Repository): BaseViewModel() {

    val detailMovie: MutableLiveData<MovieDetail> = MutableLiveData()

    fun fetchData(id: Int) {
        scope.launch {
            detailMovie.postValue(
                repository.requestMovie(id)
            )
        }
    }
}