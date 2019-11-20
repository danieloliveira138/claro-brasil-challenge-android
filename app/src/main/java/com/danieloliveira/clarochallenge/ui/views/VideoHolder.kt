package com.danieloliveira.clarochallenge.ui.views

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.danieloliveira.clarochallenge.models.Video
import kotlinx.android.synthetic.main.video_holder.view.*

class VideoHolder(private val view: View): RecyclerView.ViewHolder(view) {

    fun bind(video: Video) {
        view.videoTitle.text = video.name
        view.videoSite.text = video.site
    }
}