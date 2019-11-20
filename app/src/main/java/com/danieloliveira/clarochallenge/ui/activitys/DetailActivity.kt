package com.danieloliveira.clarochallenge.ui.activitys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.danieloliveira.clarochallenge.R
import com.danieloliveira.clarochallenge.enums.PosterImages
import com.danieloliveira.clarochallenge.enums.StringContants
import com.danieloliveira.clarochallenge.models.MovieDetail
import com.danieloliveira.clarochallenge.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.activity_detail.*
import org.koin.android.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    private val model: DetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        setupUiComponents()

        setupObservers()
    }

    private fun setupObservers() {
        model.detailMovie.observe(this, Observer {

        })
    }

    private fun setupUiComponents() {
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val intent = intent
        if (intent.hasExtra(StringContants.MOVIE_ID.const)){
            model.fetchData(intent.getIntExtra(StringContants.MOVIE_ID.const, -1))
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        finish()
        super.onBackPressed()
    }

    fun progressBar(enable: Boolean?): Boolean {
        if(enable != null){
            when(enable) {
                true -> progressBar.visibility = View.VISIBLE
                false -> progressBar.visibility = View.GONE
            }
        }
        return progressBar.visibility == View.VISIBLE
    }

    override fun insertMovie(movieDetail: MovieDetail) {
        movieTitle.text = movieDetail.title

        movieGenre.text = presenter.getMovieGenres(movieDetail.genres)

        movieRelease.text = presenter.getReleaseDate(movieDetail.release_date)

        movieRealTitle.text = movieDetail.original_title

        overview.text = presenter.getOverview(movieDetail.overview)

        classification.text = presenter.getClassification(movieDetail.adult)

        votes.text = presenter.getVotes(movieDetail.vote_count)

        var options = RequestOptions()
        options = options.transform(CenterCrop(), RoundedCorners(16))

        Glide.with(this)
            .load(PosterImages.W500.getImageUrl(movieDetail.backdrop_path))
            .transition(DrawableTransitionOptions.withCrossFade())
            .apply(options)
            .into(movieBanner)

        Glide.with(this)
            .load(PosterImages.W500.getImageUrl(movieDetail.poster_path))
            .placeholder(R.drawable.the_movie_guide_place_holder)
            .transition(DrawableTransitionOptions.withCrossFade())
            .apply(options)
            .into(moviePoster)

        movieDetail.vote_average?.let {
            voteAverage.text = try {
                it.toString()
            } catch (e: Exception) {
                "N/A"
            }
            voteAverage.visibility = View.VISIBLE
        }

        movieDetail.adult?.let {
            adultContent.visibility = if (it) View.VISIBLE else View.GONE
        }
    }

    fun errorMessage(error: ErrorResponse) {
        Toast.makeText(this, error.errorMessage, Toast.LENGTH_SHORT).show()
    }

}
