package com.ahmed.habib.utils.broadcast_receiver.bootAndHeadReceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.ahmed.habib.utils.services.background_service.MyBackgroundService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class BootAndHeadSetReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        if (intent.action == Intent.ACTION_BOOT_COMPLETED) {

            // To tell the system that not finish the onReceive until finish my heavy work
            val result = goAsync()

            CoroutineScope(Dispatchers.IO).launch {
                delay(50000)
                Toast.makeText(context, "Device Booted.", Toast.LENGTH_SHORT).show()

                result.finish()
            }

        } else if (intent.action == Intent.ACTION_HEADSET_PLUG) {

            val intentService = Intent(context, MyBackgroundService::class.java)

            when (intent.getIntExtra("state", -1)) {
                0 -> {
                    context.stopService(intentService)
                    Toast.makeText(context, "Plugged Off.", Toast.LENGTH_SHORT).show()
                }

                1 -> {
                    context.startService(intentService)
                    Toast.makeText(context, "Plugged In.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}