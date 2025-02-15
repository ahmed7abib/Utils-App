package com.ahmed.habib.utils.locationTracking.ui

import android.Manifest
import android.app.Activity
import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ahmed.habib.core.composeable.CommonRoundedButton
import com.ahmed.habib.core.utils.openAppSettings
import com.ahmed.habib.core.utils.showConfirmationDialog
import com.ahmed.habib.utils.BaseApp
import com.ahmed.habib.utils.R
import com.ahmed.habib.utils.locationTracking.LocationService

@Composable
fun LocationTrackingScreen(activity: Activity) {

    var rememberLocationPermissionState: Boolean
    var rememberActionClicked by remember { mutableStateOf("START") }

    val locationRequestPermission =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestMultiplePermissions(),
            onResult = {
                rememberLocationPermissionState =
                    it[Manifest.permission.ACCESS_FINE_LOCATION] == true
                            && it[Manifest.permission.ACCESS_FINE_LOCATION] == true

                val action = if (rememberActionClicked == "START") {
                    BaseApp.ACTION_START
                } else {
                    BaseApp.ACTION_STOP
                }

                if (rememberLocationPermissionState) {
                    Intent(activity, LocationService::class.java).apply {
                        this.action = action
                        activity.startService(this)
                    }
                } else {
                    activity.showConfirmationDialog(
                        message = activity.getString(R.string.accept_location_permis),
                        title = activity.getString(R.string.app_name)
                    ) {
                        activity.openAppSettings()
                    }
                }
            }
        )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CommonRoundedButton(
            name = "Start",
            fontSize = 14.sp,
            mainColor = R.color.purple_200,
            textColor = R.color.white
        ) {
            rememberActionClicked = "START"
            locationRequestPermission.launch(
                arrayOf(
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                )
            )
        }

        Spacer(modifier = Modifier.heightIn(8.dp))

        CommonRoundedButton(
            name = "Stop",
            fontSize = 14.sp,
            mainColor = R.color.purple_200,
            textColor = R.color.white
        ) {
            rememberActionClicked = "STOP"
            locationRequestPermission.launch(
                arrayOf(
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                )
            )
        }
    }
}