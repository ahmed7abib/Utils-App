package com.ahmed.habib.utils.locationTracking

import android.annotation.SuppressLint
import android.content.Context
import android.os.Looper
import com.google.android.gms.location.*
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch

@SuppressLint("MissingPermission")
class LocationClient(
    private val context: Context,
    private val client: FusedLocationProviderClient
) : ILocationClient {

    override fun getLocationUpdates(
        interval: Long,
        callback: GetLocationProcesses
    ) {
        if (!context.hasLocationPermission()) {
            callback.needLocationPermission()
            return
        }

        if (!context.isGpsAvailable()) {
            callback.needOpenGpsOrLocation()
            return
        }

        callback.getLocation(callbackFlow {
            val request = LocationRequest.Builder(Priority.PRIORITY_LOW_POWER, interval).build()

            val locationCallback = object : LocationCallback() {
                override fun onLocationResult(result: LocationResult) {
                    super.onLocationResult(result)
                    result.locations.lastOrNull()?.let { location ->
                        launch {
                            send(location)
                        }
                    }
                }
            }

            client.requestLocationUpdates(
                request,
                locationCallback,
                Looper.getMainLooper()
            )

            awaitClose {
                client.removeLocationUpdates(locationCallback)
            }
        })
    }
}