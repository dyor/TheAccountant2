package com.example.theaccountant2.util

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent?) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        // Show the notification
        NotificationUtils.showNotification(context)
    }
}
