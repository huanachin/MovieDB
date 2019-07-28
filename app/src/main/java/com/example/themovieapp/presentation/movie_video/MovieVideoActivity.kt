package com.example.themovieapp.presentation.movie_video

import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import com.example.themovieapp.R
import com.example.themovieapp.utils.Constants
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView

class MovieVideoActivity : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {

    private lateinit var videoKey: String
    private lateinit var youTubeView: YouTubePlayerView

    companion object {
        const val VIDEO_KEY = "VIDEO_KEY"
        const val RECOVERY_DIALOG_REQUEST = 1
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_movie_video)
        videoKey = intent?.getStringExtra(VIDEO_KEY)!!
        init()
    }

    fun init() {
        youTubeView = findViewById(R.id.youtube_view)
        youTubeView.initialize(Constants.API_GOOGLE_KEY, this)
    }

    override fun onInitializationSuccess(
        provider: YouTubePlayer.Provider?,
        player: YouTubePlayer?,
        wasRestored: Boolean
    ) {
        if (!wasRestored) {
            player?.cueVideo(videoKey)
        }
    }

    override fun onInitializationFailure(provider: YouTubePlayer.Provider?, result: YouTubeInitializationResult?) {
        if (result!!.isUserRecoverableError) {
            result.getErrorDialog(this, RECOVERY_DIALOG_REQUEST).show()
        } else {
            Toast.makeText(
                this,
                "YouTubePlayer.onInitializationFailure(): $result",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == RECOVERY_DIALOG_REQUEST) {
            getYouTubePlayerProvider().initialize(Constants.API_GOOGLE_KEY, this)
        }
    }

    private fun getYouTubePlayerProvider(): YouTubePlayer.Provider {
        return findViewById<YouTubePlayerView>(R.id.youtube_view)
    }

}