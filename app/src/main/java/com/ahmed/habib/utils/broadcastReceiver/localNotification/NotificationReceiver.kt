package com.ahmed.habib.utils.broadcastReceiver.localNotification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class NotificationReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent?) {
        LocalNotificationsWithActionBtn(context).createNotification(++Counter.value)
        // Here you can call api
        // Here you can do any thing from notification action button
    }
}

object Counter {
    var value = 0
}