package com.danieloliveira.clarochallenge.ui.activitys

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.annotation.SuppressLint
import android.util.SparseArray
import at.huber.youtubeExtractor.VideoMeta
import at.huber.youtubeExtractor.YouTubeExtractor
import at.huber.youtubeExtractor.YtFile
import com.danieloliveira.clarochallenge.exoplayer.ExoPlayerManager
import android.net.Uri
import android.view.View
import com.danieloliveira.clarochallenge.R.*
import com.danieloliveira.clarochallenge.callbacks.CallBacks
import com.danieloliveira.clarochallenge.enums.StringContants
import kotlinx.android.synthetic.main.activity_video.*
import org.jetbrains.anko.toast


class VideoActivity : AppCompatActivity(), CallBacks.playerCallBack {

    private val youtubeBaseUrl = "https://www.youtube.com"
    private var mYoutubeLink : String? = null //=

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_video)

        if(intent.hasExtra(StringContants.MOVIE_VIDEO_ID.const)){
            mYoutubeLink = "$youtubeBaseUrl/watch?v=${intent.getStringExtra(StringContants.MOVIE_VIDEO_ID.const)}"
            extractYoutubeUrl()
            progressBar.visibility = View.VISIBLE
        } else {
            toast("Impossível reproduzir este video no momento.")
            onBackPressed()
        }

    }

    private fun extractYoutubeUrl() {
        @SuppressLint("StaticFieldLeak")
        val mExtractor: YouTubeExtractor = object: YouTubeExtractor(this) {
            override fun onExtractionComplete(
                ytFiles: SparseArray<YtFile>?, videoMeta: VideoMeta?) {
                if (ytFiles != null) {
                    // 720, 1080, 480, 360
                    val iTags = listOf(22, 137, 18, 17)

                    for (iTag in iTags) {

                        val ytFile = ytFiles.get(iTag)

                        if (ytFile != null) {

                            val downloadUrl = ytFile.url

                            if (downloadUrl != null && downloadUrl.isNotEmpty()) {

                                val youtubeUri = Uri.parse(downloadUrl)
                                playVideo(youtubeUri.toString())
                                progressBar.visibility = View.GONE
                                return
                            }
                        }
                    }
                }
            }
        }

        mExtractor.extract(mYoutubeLink, true, true);
    }

    private fun playVideo(downloadUrl: String) {
        videoView.player = ExoPlayerManager.getSharedInstance(this@VideoActivity).playerView.player
        ExoPlayerManager.getSharedInstance(this@VideoActivity).playStream(downloadUrl)
        ExoPlayerManager.getSharedInstance(this@VideoActivity).setPlayerListener(this)
    }

    override fun onPlayingEnd() {
        onBackPressed()
    }

    override fun onItemClickOnItem(albumId: Int?) {}

    override fun onBackPressed() {
        super.onBackPressed()
        ExoPlayerManager.getSharedInstance(this@VideoActivity).destroyPlayer()
        finish()
    }

}
