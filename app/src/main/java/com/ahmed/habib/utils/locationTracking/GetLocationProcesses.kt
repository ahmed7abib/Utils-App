package com.ahmed.habib.utils.locationTracking

import android.location.Location
import kotlinx.coroutines.flow.Flow

interface GetLocationProcesses {
    fun getLocation(data: Flow<Location>)

    fun needOpenGpsOrLocation()

    fun needLocationPermission()
}