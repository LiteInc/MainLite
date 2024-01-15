package com.inc.lite.mainlite.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log


/**
 * BroadcastReceiver implementation that handles the device boot completed event.
 * This receiver is triggered when the system completes the boot process.
 */
class OnBootUpReceiver : BroadcastReceiver() {

    /**
     * Called when a broadcast is received.
     *
     * @param context The Context in which the receiver is running.
     * @param intent The Intent being received.
     */
    override fun onReceive(context: Context?, intent: Intent?) {
        // Check if the received intent is for ACTION_BOOT_COMPLETED
        if (Intent.ACTION_BOOT_COMPLETED == intent?.action) {

            Log.i("OnBootUpReceiver", "Receiver is started on proper intent")

            // Create an intent to start the MainActivity
            val appStartIntent = Intent("com.inc.lite.station.Start")

            // Start the MainActivity using the created intent
            try{
                context!!.startActivity(appStartIntent)

            }catch (e: Exception){
                e.message?.let { Log.d("OnBootUpReceiver", it) }
            }
        }

    }
}