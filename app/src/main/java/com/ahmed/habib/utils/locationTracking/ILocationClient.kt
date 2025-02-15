package com.ahmed.habib.utils.locationTracking

interface ILocationClient {
    fun getLocationUpdates(interval: Long, callback: GetLocationProcesses)
}