package com.ahmed.habib.utils.broadcast_receiver.localNotification


import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.ahmed.habib.utils.BaseApp
import com.ahmed.habib.utils.MainActivity
import com.ahmed.habib.utils.R

class LocalNotificationsWithActionBtn(
    private val context: Context
) {

    private val notificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    fun createNotification(value: Int) {
        val activityIntent = Intent(context, MainActivity::class.java)

        val activityPendingIntent = PendingIntent.getActivity(
            context,
            1,
            activityIntent,
            PendingIntent.FLAG_IMMUTABLE
        )

        // Broad cast to receive action from notification.
        val incrementIntent = PendingIntent.getBroadcast(
            context,
            2,
            Intent(context, NotificationReceiver::class.java),
            PendingIntent.FLAG_IMMUTABLE
        )

        val notification = NotificationCompat.Builder(context, BaseApp.CHANNEL_ID)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("Title")
            .setContentText("Message : $value")
            .setContentIntent(activityPendingIntent)
            .addAction(
                com.google.android.gms.base.R.drawable.common_full_open_on_phone,
                "Increment",
                incrementIntent
            ).build()

        notificationManager.notify(1, notification)
    }
}