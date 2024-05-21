package com.example.mediaxsample

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView

class MainActivity : AppCompatActivity() {

    lateinit var player :ExoPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        player = ExoPlayer.Builder(applicationContext).build()

        val playerView = findViewById<PlayerView>(R.id.player_view);

        playerView.player = player;


    }

    override fun onResume() {
        super.onResume()
        startPlayback()
    }

    override fun onStop() {
        player.stop()
        super.onStop()
    }

    fun startPlayback() {
        var  mediaItem = MediaItem.fromUri("https://storage.googleapis.com/wvmedia/clear/h264/tears/tears.mpd")

        player.setMediaItem(mediaItem)

        player.prepare()

        player.play()
    }

}