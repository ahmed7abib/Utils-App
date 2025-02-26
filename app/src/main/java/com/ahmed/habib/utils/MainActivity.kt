package com.ahmed.habib.utils

import android.Manifest
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.ahmed.habib.utils.alarm_manger.ui.AlarmManagerLayout
import com.ahmed.habib.utils.broadcast_receiver.BroadCastScreen
import com.ahmed.habib.utils.content_provider.ui.ContentProviderScreen
import com.ahmed.habib.utils.list_of_timers.ListOfTimersActivity
import com.ahmed.habib.utils.pick_image.ui.PickCameraImageLayout
import com.ahmed.habib.utils.pick_image.ui.PickImageLayout
import com.ahmed.habib.utils.location_tracking.ui.LocationTrackingScreen
import com.ahmed.habib.utils.services.ServicesScreen
import com.ahmed.habib.utils.work_manager.ui.WorkManagerLayout

class MainActivity : ComponentActivity() {

    private val startTimerListActivity = {
        val intent = Intent(this, ListOfTimersActivity::class.java)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            registerForNotificationsPermission.launch(Manifest.permission.POST_NOTIFICATIONS)
        }

        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
            ) {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Screens.MainScreen.route,
                ) {
                    composable(route = Screens.MainScreen.route) {
                        MainScreen(
                            navController,
                            navigateToTimerListActivity = startTimerListActivity
                        )
                    }

                    composable(route = Screens.ServicesScreen.route) {
                        ServicesScreen(this@MainActivity)
                    }

                    composable(route = Screens.BroadCastReceiverScreen.route) {
                        BroadCastScreen()
                    }

                    composable(route = Screens.LocationTrackingScreen.route) {
                        LocationTrackingScreen(this@MainActivity)
                    }

                    composable(route = Screens.ContentProviderScreen.route) {
                        ContentProviderScreen()
                    }

                    composable(route = Screens.PickImageScreen.route) {
                        PickImageLayout()
                    }

                    composable(route = Screens.PickCameraImageScreen.route) {
                        PickCameraImageLayout()
                    }

                    composable(route = Screens.AlarmManagerScreen.route) {
                        AlarmManagerLayout()
                    }

                    composable(route = Screens.WorkManagerScreen.route) {
                        WorkManagerLayout()
                    }
                }
            }
        }
    }

    private val registerForNotificationsPermission =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
            if (!granted) {
                Toast.makeText(
                    this,
                    "You will not receive any notifications from the app!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
}