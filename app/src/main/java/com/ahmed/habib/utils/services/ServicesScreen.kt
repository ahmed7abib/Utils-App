package com.ahmed.habib.utils.services

import android.app.Activity
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.ahmed.habib.core.composeable.CommonRoundedButton
import com.ahmed.habib.core.utils.showToast
import com.ahmed.habib.utils.R
import com.ahmed.habib.utils.services.background_service.MyBackgroundService
import com.ahmed.habib.utils.services.bound_service.MyBoundService
import com.ahmed.habib.utils.services.foreground_service.MyForegroundService

@Composable
fun ServicesScreen(activity: Activity) {

    var isBounding = false
    lateinit var boundService: MyBoundService

    val intent = Intent(activity, MyBoundService::class.java).apply {
        putExtra("FIRST_NUMBER", 10)
        putExtra("SECOND_NUMBER", 20)
    }

    val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(p0: ComponentName?, binder: IBinder?) {
            isBounding = true
            val service = binder as MyBoundService.BoundServiceBinder
            boundService = service.getService()
        }

        override fun onServiceDisconnected(p0: ComponentName?) {
            isBounding = false
        }
    }

    activity.bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)

    Column(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxSize()
    ) {

        Text(
            text = "Background Service",
            color = Color.Black,
            textAlign = TextAlign.Center,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 20.dp)
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.padding(top = 16.dp)
        ) {
            CommonRoundedButton(
                name = "Start Background Service",
                fontSize = 16.sp,
                mainColor = R.color.purple_700,
                textColor = R.color.white,
                modifier = Modifier
                    .weight(1f)
            ) {
                activity.startService(Intent(activity, MyBackgroundService::class.java))
            }

            CommonRoundedButton(
                name = "Stop Background Service",
                fontSize = 16.sp,
                mainColor = R.color.purple_700,
                textColor = R.color.white,
                modifier = Modifier
                    .weight(1f)
            ) {
                activity.stopService(Intent(activity, MyBackgroundService::class.java))
            }
        }

        Divider(
            thickness = 1.dp,
            color = Color.LightGray,
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
                .height(1.dp)
        )

        Text(
            text = "Foreground Service",
            color = Color.Black,
            textAlign = TextAlign.Center,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 20.dp)
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.padding(top = 16.dp)
        ) {
            CommonRoundedButton(
                name = "Start Foreground Service",
                fontSize = 16.sp,
                mainColor = R.color.purple_700,
                textColor = R.color.white,
                modifier = Modifier
                    .weight(1f)
            ) {
                ContextCompat.startForegroundService(
                    activity,
                    Intent(activity, MyForegroundService::class.java)
                )
            }

            CommonRoundedButton(
                name = "Stop Foreground Service",
                fontSize = 16.sp,
                mainColor = R.color.purple_700,
                textColor = R.color.white,
                modifier = Modifier
                    .weight(1f)
            ) {
                activity.stopService(Intent(activity, MyForegroundService::class.java))
            }
        }

        Divider(
            thickness = 1.dp,
            color = Color.LightGray,
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
                .height(1.dp)
        )

        Text(
            text = "Bound Service",
            color = Color.Black,
            textAlign = TextAlign.Center,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 20.dp)
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.padding(top = 16.dp)
        ) {
            CommonRoundedButton(
                name = "Start Bound Service",
                fontSize = 16.sp,
                mainColor = R.color.purple_700,
                textColor = R.color.white,
                modifier = Modifier
                    .weight(1f)
            ) {
                activity.showToast("${boundService.getResult()}")
            }

            CommonRoundedButton(
                name = "Stop Bound Service",
                fontSize = 16.sp,
                mainColor = R.color.purple_700,
                textColor = R.color.white,
                modifier = Modifier
                    .weight(1f)
            ) {
                if (isBounding) {
                    isBounding = false
                    activity.unbindService(serviceConnection)
                }
            }
        }
    }
}