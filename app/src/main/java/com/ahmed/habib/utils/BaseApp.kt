package com.ahmed.habib.utils

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context

class BaseApp : Application() {

    companion object{
        const val ACTION_STOP = "ACTION_STOP"
        const val ACTION_START = "ACTION_START"
        const val CHANNEL_ID = "CHANNEL_ID"
        const val CHANNEL_NAME = "Habib Utils Channel"
    }

    override fun onCreate() {
        super.onCreate()

        val channel = NotificationChannel(
            BaseApp.CHANNEL_ID,
            BaseApp.CHANNEL_NAME,
            NotificationManager.IMPORTANCE_DEFAULT
        )

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        notificationManager.createNotificationChannel(channel)
    }
}
