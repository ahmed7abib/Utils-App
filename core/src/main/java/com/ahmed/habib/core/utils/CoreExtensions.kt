package com.ahmed.habib.core.utils

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.Intent.*
import android.graphics.Typeface
import android.net.Uri
import android.provider.Settings
import android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS
import android.widget.TextView
import android.widget.Toast
import com.ahmed.habib.core.R
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.util.*

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Context.setTypeFace(view: List<TextView>, font: String) {
    view.forEach {
        it.typeface = Typeface.createFromAsset(this.assets, font)
    }
}

fun getRandom(from: Int, to: Int) = (Math.random() * (to - from + 1) + from).toInt()


fun Context.showConfirmationDialog(message: String, title: String, action: () -> Unit) {
    AlertDialog.Builder(this).setTitle(title).setMessage(message)
        .setPositiveButton(getString(R.string._ok)) { _, _ ->
            action.invoke()
        }.setNegativeButton(getString(R.string.cancel), null).show()
}


fun Context.openAppSettings() {
    val intent = Intent(ACTION_APPLICATION_DETAILS_SETTINGS)
    with(intent) {
        data = Uri.fromParts("package", packageName, null)
        addCategory(CATEGORY_DEFAULT)
        addFlags(FLAG_ACTIVITY_NEW_TASK)
        addFlags(FLAG_ACTIVITY_NO_HISTORY)
        addFlags(FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS)
    }
    startActivity(intent)
}

fun Context.openDeviceGps() {
    val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
    intent.addFlags(FLAG_ACTIVITY_NEW_TASK)
    startActivity(intent)
}

fun getMilliFromDateTime(
    dayOfMonth: Int,
    month: Int,
    year: Int,
    hour: Int,
    minutes: Int,
    seconds: Int
): Long {
    val calendar = Calendar.getInstance()

    calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
    calendar.set(Calendar.MONTH, month)
    calendar.set(Calendar.YEAR, year)

    calendar.set(Calendar.HOUR, hour)
    calendar.set(Calendar.MINUTE, minutes)
    calendar.set(Calendar.SECOND, seconds)

    return calendar.timeInMillis
}

fun getMilliFromTime(dateOrTime: String, pattern: String): Long? {
    return if (isValidFormat(pattern, dateOrTime)) {
        var amPM: Int
        val calendar = Calendar.getInstance()

        val timeArray: List<String> = dateOrTime.trim().replace(" ", ":").split(":")

        timeArray[3].lowercase().apply { amPM = if (this == "pm") Calendar.PM else Calendar.AM }

        calendar.set(Calendar.HOUR, timeArray[0].toInt())
        calendar.set(Calendar.MINUTE, timeArray[1].toInt())
        calendar.set(Calendar.SECOND, timeArray[2].toInt())
        calendar.set(Calendar.AM_PM, amPM)

        return calendar.timeInMillis
    } else {
        null
    }
}

private fun isValidFormat(format: String, value: String): Boolean {
    val ldt: LocalDateTime?
    val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern(format, Locale.ENGLISH)

    try {
        ldt = LocalDateTime.parse(value, formatter)
        val result: String = ldt.format(formatter)
        return result == value
    } catch (e: DateTimeParseException) {
        return try {
            val ld: LocalDate = LocalDate.parse(value, formatter)
            val result: String = ld.format(formatter)
            result == value
        } catch (exp: DateTimeParseException) {
            try {
                val lt: LocalTime = LocalTime.parse(value, formatter)
                val result: String = lt.format(formatter)
                result == value
            } catch (e2: DateTimeParseException) {
                false
            }
        }
    }
}