package com.inc.lite.main.receivers

import android.content.ActivityNotFoundException
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.content.ContextCompat.startActivity
import com.inc.lite.main.MainActivity

/**
 * BroadcastReceiver implementation that handles the device boot completed event.
 * This receiver is triggered when the system completes the boot process.
 */
class StartUpOnBootReceiver: BroadcastReceiver() {

    /**
     * Called when a broadcast is received.
     *
     * @param context The Context in which the receiver is running.
     * @param intent The Intent being received.
     */
    override fun onReceive(context: Context?, intent: Intent?) {

        if (Intent.ACTION_BOOT_COMPLETED == intent?.action) {

            Log.i("StartUpOnBootUpReceiver", "Receiver is started on proper intent")

            // Create an intent to start StationLite Apk
            val appStartIntent = Intent().apply {
                action = "com.inc.lite.station.Start"
            }

            // Create an intent to start the MainActivity
            val mainActivity = Intent(context!!,MainActivity::class.java).apply{
                this.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }

            // Try to start the stationLite if catch exception start MainActivity
            try {
                context.startActivity(appStartIntent)
            } catch (e: ActivityNotFoundException) {

                context.startActivity(mainActivity)
            }
        }

        TODO("action on start station")
    }
}