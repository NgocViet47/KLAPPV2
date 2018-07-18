package com.example.viet.klappv.service

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class NotificationFireBase : FirebaseMessagingService() {
    override fun onMessageReceived(p0: RemoteMessage?) {
        Log.e( "From: " , p0.toString())

        // Check if message contains a data payload.
        if (p0 != null) {
            if (p0.data.isNotEmpty()) {
                Log.e( "Message data payload: ", p0.data.toString())
            }
        }

        // Check if message contains a notification payload.
        if (p0 != null) {
            if (p0.notification != null) {
                Log.e("Notification Body: " , p0.notification!!.body)
            }
        }
        super.onMessageReceived(p0)
    }
}
