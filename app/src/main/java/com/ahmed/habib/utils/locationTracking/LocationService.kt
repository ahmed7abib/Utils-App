package com.ahmed.habib.utils.locationTracking

import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.location.Location
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.google.android.gms.location.LocationServices
import com.ahmed.habib.core.utils.openDeviceGps
import com.ahmed.habib.core.utils.showToast
import com.ahmed.habib.utils.BaseApp
import com.ahmed.habib.utils.R
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*


class LocationService : Service() {

    private lateinit var locationClient: LocationClient
    private val serviceScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()

        locationClient =
            LocationClient(
                this,
                LocationServices.getFusedLocationProviderClient(this)
            )
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        when (intent?.action) {
            BaseApp.ACTION_START -> start()
            BaseApp.ACTION_STOP -> stop(intent)
        }

        return START_STICKY
    }

    private fun start() {
        val notification =
            NotificationCompat.Builder(this, BaseApp.CHANNEL_ID).setOngoing(true)
                .setAutoCancel(true)
                .setContentTitle("Location Tracking.")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentText("App is running on the background")

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        locationClient.getLocationUpdates(10000L, object : GetLocationProcesses {
            override fun getLocation(data: Flow<Location>) {

                data.catch { e ->
                    e.printStackTrace()
                }.onEach { location ->

                    val lat = location.latitude.toString()
                    val long = location.longitude.toString()

                    withContext(Dispatchers.Main) { showToast("$lat : $long") }

                    if (!isServiceRunningInForeground(LocationService::class.java)) {
                        startForeground(1, notification.build())
                        notificationManager.notify(1, notification.build())
                    }
                }.launchIn(serviceScope)
            }

            override fun needOpenGpsOrLocation() {
                openDeviceGps()
            }

            override fun needLocationPermission() {
                showToast(getString(R.string.accept_all_permissions))
            }
        })
    }

    private fun stop(intent: Intent) {
        if (isServiceRunningInForeground(LocationService::class.java)) {
            stopService(intent)
            stopSelf()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        serviceScope.cancel()
    }
}

