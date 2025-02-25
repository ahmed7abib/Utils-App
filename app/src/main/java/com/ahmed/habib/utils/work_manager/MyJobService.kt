package com.ahmed.habib.utils.work_manager

import android.app.NotificationManager
import android.app.PendingIntent
import android.app.job.JobParameters
import android.app.job.JobService
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import com.ahmed.habib.utils.BaseApp
import com.ahmed.habib.utils.MainActivity
import com.ahmed.habib.utils.R

class MyJobService : JobService() {

    override fun onStartJob(jobParamters: JobParameters?): Boolean {
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        createNotification(this, notificationManager)

        return false
    }

    override fun onStopJob(jobParamters: JobParameters?): Boolean {
        Log.i("TAG", "onStopJob:")
        return true
    }

    private fun createNotification(
        context: Context,
        notificationManager: NotificationManager
    ) {
        val activityIntent = Intent(context, MainActivity::class.java)

        val activityPendingIntent = PendingIntent.getActivity(
            context,
            1,
            activityIntent,
            PendingIntent.FLAG_IMMUTABLE
        )

        val notification = NotificationCompat.Builder(context, BaseApp.CHANNEL_ID)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("Alarm Manger")
            .setContentText("JobService")
            .setContentIntent(activityPendingIntent)
            .build()

        notificationManager.notify(1, notification)
    }
}