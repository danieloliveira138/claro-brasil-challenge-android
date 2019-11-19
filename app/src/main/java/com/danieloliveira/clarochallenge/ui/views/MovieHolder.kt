package com.danieloliveira.clarochallenge.ui.views

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.danieloliveira.clarochallenge.R
import com.danieloliveira.clarochallenge.enums.PosterImages
import com.danieloliveira.clarochallenge.enums.TextStyleSpannable
import com.danieloliveira.clarochallenge.models.Movie
import com.danieloliveira.clarochallenge.utils.TextCustomUtils
import kotlinx.android.synthetic.main.movie_holder.view.*

class MovieHolder(val view: View): RecyclerView.ViewHolder(view) {

    fun bind(movie: Movie, clickMovie: (Int) -> Unit){
        view.movieTitle.text = movie.title

        view.releaseYear.text =
            TextStyleSpannable.ITALIC.spannableText(
                "Lançamento: ",
                TextCustomUtils.getYear(movie.release_date)
            )

        var options = RequestOptions()
        options = options.transform(CenterCrop(), RoundedCorners(16))

        Glide.with(view.context)
            .load(PosterImages.W200.getImageUrl(movie.poster_path))
            .placeholder(R.drawable.the_movie_guide_place_holder)
            .transition(DrawableTransitionOptions.withCrossFade())
            .apply(options)
            .into(view.movieImage)

        movie.vote_average?.let {
            view.voteAverage.text = try {
                it.toString()
            } catch (e: Exception) {
                "0,0"
            }
        }

        movie.adult?.let {
            view.adultContent.visibility = if (it) View.VISIBLE else View.GONE
        }

        view.setOnClickListener { clickMovie(movie.id!!) }
    }
}