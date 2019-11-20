package com.danieloliveira.clarochallenge.viewmodel

import android.text.SpannableString
import androidx.lifecycle.MutableLiveData
import com.danieloliveira.clarochallenge.enums.TextStyleSpannable
import com.danieloliveira.clarochallenge.models.Genre
import com.danieloliveira.clarochallenge.models.Movie
import com.danieloliveira.clarochallenge.models.MovieDetail
import com.danieloliveira.clarochallenge.models.VideoResponse
import com.danieloliveira.clarochallenge.source.Repository
import com.danieloliveira.clarochallenge.utils.TextCustomUtils
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: Repository): BaseViewModel() {

    val detailMovie: MutableLiveData<MovieDetail> = MutableLiveData()
    val movieVideos: MutableLiveData<VideoResponse> = MutableLiveData()
    val favoriteMovie: MutableLiveData<Movie> = MutableLiveData()
    var movie: Movie? = null
    var isFavorite: Boolean = false

    fun loadMovie(id: Int) {
        scope.launch {
            detailMovie.postValue(
                repository.requestMovie(id)
            )
        }
    }

    fun loadVideos(id: Int) {
        scope.launch {
            movieVideos.postValue(
                repository.requestVideos(id)
            )
        }
    }

    fun setFavoriteMovie() {
        if (movie == null) return

        if (isFavorite) {
            scope.launch {
                repository.deleteMovie(movie = movie!!)
                favoriteMovie.postValue(null)
            }
            return
        }

        scope.launch {
            repository.setFavoriteMovie(movie = movie!!)
            favoriteMovie.postValue(
                repository.getFavoriteMovie(id = movie!!.id!!)
            )
        }
    }

    fun getFavoriteMovie(id: Int?) {
        id?.let {
            scope.launch {
                favoriteMovie.postValue(
                    repository.getFavoriteMovie(id = it)
                )
            }
        }

    }

    fun getMovieGenres(genres: List<Genre>?): String {
        var text = ""
        if (!genres.isNullOrEmpty()){
            text = "("
            for (i in 0..genres.lastIndex){
                text += genres[i].name
                text += if (i < genres.lastIndex) " / " else ")"
            }
        }
        return text
    }

    fun getReleaseDate(release: String?): String {
        return TextCustomUtils.getFormattedDateText(release)
    }

    fun getOverview(overview: String?): SpannableString =
        TextStyleSpannable.ITALIC.spannableText("Sinopse: ", overview)

    fun getClassification(classification: Boolean?): SpannableString {
        val classificationText = if (classification == null || !classification) "Não" else "Sim"
        return TextStyleSpannable.BOLD.spannableText("Filme adulto: ", classificationText)
    }

    fun getVotes(votes: Int?): SpannableString {
        val totalVotes = if (votes == null || votes <= 0) 0 else votes
        return TextStyleSpannable.BOLD.spannableText("Total de votos: ", totalVotes.toString())
    }

}