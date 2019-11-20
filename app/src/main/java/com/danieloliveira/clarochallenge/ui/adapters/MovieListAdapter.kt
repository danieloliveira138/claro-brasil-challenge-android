package com.danieloliveira.clarochallenge.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.danieloliveira.clarochallenge.R
import com.danieloliveira.clarochallenge.models.Movie
import com.danieloliveira.clarochallenge.models.MovieList
import com.danieloliveira.clarochallenge.ui.views.MovieHolder
import com.danieloliveira.clarochallenge.utils.logIt

class MovieListAdapter(private val clickMovie: (Int) -> Unit): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val movieList = ArrayList<Movie>()

    fun insertMovieList(movieList: MovieList){
        movieList.results?.let {
            this.movieList.clear()
            this.movieList.addAll(it)
            notifyDataSetChanged()
        }
    }

    fun addMovieList(movieList: MovieList){
        movieList.results?.let {
            this.movieList.addAll(it)
            notifyDataSetChanged()
            "notified".logIt()
        }
    }

    fun clearMovieList(){
        movieList.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_holder, parent, false)
        return MovieHolder(view)
    }

    override fun getItemCount(): Int = movieList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as MovieHolder
        holder.bind(movieList[position], clickMovie)
    }

}