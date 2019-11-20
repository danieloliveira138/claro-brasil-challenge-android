package com.danieloliveira.clarochallenge.ui.activitys

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
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
import com.danieloliveira.clarochallenge.models.Video
import com.danieloliveira.clarochallenge.ui.views.VideoHolder
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
            it?.let {
                insertMovie(it)
            }
        })

        model.movieVideos.observe(this, Observer {
            it?.let {
                createVideosList(it.results)
            }
        })
    }

    private fun setupUiComponents() {
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val intent = intent
        if (intent.hasExtra(StringContants.MOVIE_ID.const)){
            model.loadMovie(intent.getIntExtra(StringContants.MOVIE_ID.const, -1))
            model.loadVideos(intent.getIntExtra(StringContants.MOVIE_ID.const, -1))
            progressBar.visibility = View.VISIBLE
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

    private fun insertMovie(movieDetail: MovieDetail) {
        movieTitle.text = movieDetail.title

        movieGenre.text = model.getMovieGenres(movieDetail.genres)

        movieRelease.text = model.getReleaseDate(movieDetail.release_date)

        movieRealTitle.text = movieDetail.original_title

        overview.text = model.getOverview(movieDetail.overview)

        classification.text = model.getClassification(movieDetail.adult)

        votes.text = model.getVotes(movieDetail.vote_count)

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

        progressBar.visibility = View.GONE

    }

    private fun sendToVideoActivity(movieKey: String) {
        val intent = Intent(this, VideoActivity::class.java)
        intent.putExtra(StringContants.MOVIE_VIDEO_ID.const, movieKey)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

    private fun createVideosList(videos: List<Video>) {
        for(video in videos){
            val inflater = LayoutInflater.from(this)
            val view = inflater.inflate(R.layout.video_holder, videosList, false)
            val holder = VideoHolder(view)
            holder.bind(video)
            holder.itemView.setOnClickListener {
                sendToVideoActivity(video.key)
            }
            videosList.addView(holder.itemView)
        }
    }

}
