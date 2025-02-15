package com.ahmed.habib.utils.alarmManger

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.ahmed.habib.core.utils.getMilliFromTime
import com.ahmed.habib.core.utils.showToast
import com.ahmed.habib.utils.R

class AndroidIAlarmScheduler(private val context: Context) : IAlarmScheduler {

    companion object {
        const val timePattern = "hh:mm:ss a"
        const val extraMessage = "EXTRA_MESSAGE"
    }

    private val alarmManager = context.getSystemService(AlarmManager::class.java)

    override fun schedule(item: AlarmModel?) {
        if (item != null) {
            val dateOrTime = getMilliFromTime(item.time, timePattern)

            if (dateOrTime == null) {
                context.showToast(context.getString(R.string.enter_with_this_format) + " $timePattern")
            } else {
                val intent = Intent(context, AlarmBroadcastReceiver::class.java).apply {
                    putExtra(extraMessage, item.message)
                }

                alarmManager.setExactAndAllowWhileIdle(
                    AlarmManager.RTC_WAKEUP,
                    dateOrTime,
                    PendingIntent.getBroadcast(
                        context,
                        item.hashCode(),
                        intent,
                        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
                    )
                )
            }
        } else {
            context.showToast(context.getString(R.string.declare_alarm_first))
        }
    }

    override fun cancel(item: AlarmModel?) {
        if (item != null) {
            alarmManager.cancel(
                PendingIntent.getBroadcast(
                    context,
                    item.hashCode(),
                    Intent(context, AlarmBroadcastReceiver::class.java),
                    PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
                )
            )
        } else {
            context.showToast(context.getString(R.string.no_alarm_found))
        }
    }
}