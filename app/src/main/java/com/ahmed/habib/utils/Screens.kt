package com.ahmed.habib.utils

sealed class Screens(val route: String) {

    object MainScreen : Screens(mainScreen)
    object ServicesScreen : Screens(servicesScreen)
    object WorkManagerScreen : Screens(workManagerScreen)
    object PickImageScreen : Screens(pickImageScreen)
    object AlarmManagerScreen : Screens(alarmManagerScreen)
    object PickCameraImageScreen : Screens(pickCameraImageScreen)
    object LocationTrackingScreen : Screens(locationTrackingScreen)
    object BroadCastReceiverScreen : Screens(broadCastReceiverScreen)
    object ContentProviderScreen : Screens(contentProviderScreen)

    companion object {
        const val mainScreen = "main_screen"
        const val servicesScreen = "services_screen"
        const val workManagerScreen = "workManager_Screen"
        const val pickImageScreen = "pickImageScreen"
        const val alarmManagerScreen = "alarmManagerScreen"
        const val pickCameraImageScreen = "pickCameraImageScreen"
        const val locationTrackingScreen = "locationTrackingScreen"
        const val broadCastReceiverScreen = "broadCastReceiverScreen"
        const val contentProviderScreen = "contentProviderScreen"
    }
}
