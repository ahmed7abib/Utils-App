package com.ahmed.habib.utils.alarmManger

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class BootBroadcastReceiver : BroadcastReceiver() {

    // When mobile restart again - Re-schedule alarm again

    override fun onReceive(context: Context, intent: Intent?) {
        if (intent?.action == Intent.ACTION_BOOT_COMPLETED) {
            // Here you will get the last time that you cashed it
            // Re-Schedule Alarm again with time that you cashed.
        }
    }
}