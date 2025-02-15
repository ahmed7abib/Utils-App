package com.ahmed.habib.utils.locationTracking

import android.Manifest
import android.app.ActivityManager
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import androidx.core.content.ContextCompat


fun Context.hasLocationPermission(): Boolean {
    return ContextCompat.checkSelfPermission(
        this, Manifest.permission.ACCESS_COARSE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED
            && ContextCompat.checkSelfPermission(
        this, Manifest.permission.ACCESS_FINE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED
}

fun Context.isGpsAvailable(): Boolean {
    val locationManager =
        getSystemService(Context.LOCATION_SERVICE) as LocationManager

    val isGpsEnabled =
        locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)

    val isNetworkEnabled =
        locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)

    return isGpsEnabled && isNetworkEnabled
}

fun Context.isServiceRunningInForeground(serviceClass: Class<*>): Boolean {
    val manager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
    for (service in manager.getRunningServices(Int.MAX_VALUE)) {
        if (serviceClass.name == service.service.className) {
            return service.foreground
        }
    }
    return false
}