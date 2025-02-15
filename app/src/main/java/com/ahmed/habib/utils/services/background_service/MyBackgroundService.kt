package com.ahmed.habib.utils.services.background_service

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import com.ahmed.habib.utils.R

class MyBackgroundService : Service() {

    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate() {
        super.onCreate()

        mediaPlayer = MediaPlayer.create(this, R.raw.sample)

        mediaPlayer.setOnCompletionListener {
            stopSelf()
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (!mediaPlayer.isPlaying) {
            mediaPlayer.start()
        }

        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()

        if (mediaPlayer.isPlaying) {
            mediaPlayer.stop()
            mediaPlayer.release()
        }
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }
}