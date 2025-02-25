package com.ahmed.habib.utils.location_tracking

interface ILocationClient {
    fun getLocationUpdates(interval: Long, callback: GetLocationProcesses)
}