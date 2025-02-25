package com.ahmed.habib.utils.broadcast_receiver

import android.content.Intent
import android.content.IntentFilter
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ahmed.habib.core.composeable.CommonRoundedButton
import com.ahmed.habib.utils.R
import com.ahmed.habib.utils.broadcast_receiver.bootAndHeadReceiver.BootAndHeadSetReceiver
import com.ahmed.habib.utils.broadcast_receiver.localNotification.Counter
import com.ahmed.habib.utils.broadcast_receiver.localNotification.LocalNotificationsWithActionBtn

@Composable
fun BroadCastScreen() {

    val context = LocalContext.current

    Column(modifier = Modifier.padding(30.dp)) {

        CommonRoundedButton(
            name = "Push Notification",
            fontSize = 16.sp,
            mainColor = R.color.purple_700,
            textColor = R.color.white
        ) {
            LocalNotificationsWithActionBtn(context = context).createNotification(Counter.value)
        }

        CommonRoundedButton(
            name = "Start Receiver",
            fontSize = 16.sp,
            mainColor = R.color.purple_700,
            textColor = R.color.white,
            modifier = Modifier.padding(top = 16.dp)
        ) {
            val bootCompletedIntent = IntentFilter(Intent.ACTION_BOOT_COMPLETED)
            context.registerReceiver(BootAndHeadSetReceiver(), bootCompletedIntent)

            val headSetIntent = IntentFilter(Intent.ACTION_HEADSET_PLUG)
            context.registerReceiver(BootAndHeadSetReceiver(), headSetIntent)
        }

        CommonRoundedButton(
            name = "Start Sound",
            fontSize = 16.sp,
            mainColor = R.color.purple_700,
            textColor = R.color.white,
            modifier = Modifier.padding(top = 16.dp)
        ) {
            val headSetIntent = IntentFilter(Intent.ACTION_HEADSET_PLUG)
            context.registerReceiver(BootAndHeadSetReceiver(), headSetIntent)
        }
    }
}