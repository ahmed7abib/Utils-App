package com.ahmed.habib.utils.services.foreground_service

import android.app.Notification
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.ahmed.habib.utils.BaseApp
import com.ahmed.habib.utils.MainActivity
import com.ahmed.habib.utils.R

class MyForegroundService : Service() {

    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate() {
        super.onCreate()

        mediaPlayer = MediaPlayer.create(this, R.raw.sample)

        mediaPlayer.setOnCompletionListener {
            stopSelf()
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        startForeground(1, getNotification())

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

    private fun getNotification(): Notification {

        val intent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        val notificationBuilder = NotificationCompat.Builder(this, BaseApp.CHANNEL_ID).apply {
            setContentTitle("Play Music")
            setContentText("Music is playing into foreground")
            setSmallIcon(R.drawable.ic_launcher_foreground)
            priority = NotificationCompat.PRIORITY_DEFAULT
            addAction(R.drawable.baseline_pause_24, "Pause", pendingIntent)
        }

        return notificationBuilder.build()
    }
}