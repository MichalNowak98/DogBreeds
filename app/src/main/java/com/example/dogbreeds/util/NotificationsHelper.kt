package com.example.dogbreeds.util

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.dogbreeds.R
import com.example.dogbreeds.view.MainActivity

class NotificationsHelper(val context: Context) {
    private val CHANNEL_ID = "Dogs channel id"
    private val NOTIFICATION_ID = 123

    fun createNotification() {
        createNotificationChanell()

        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = PendingIntent.getActivity(context, 0, intent, 0)
        val icon = BitmapFactory.decodeResource(context.resources, R.drawable.dog)

        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.dog_icon)
            .setLargeIcon(icon)
            .setContentTitle("Dogs retrieved")
            .setContentText("Dogs were loaded from backend")
            .setStyle(
                NotificationCompat.BigPictureStyle()
                    .bigPicture(icon)
                    .bigLargeIcon(null)
            )
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()

        NotificationManagerCompat.from(context).notify(NOTIFICATION_ID, notification)
    }

    private  fun createNotificationChanell() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = CHANNEL_ID
            val descriptionText = "Channel dexcription"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            val notificationManager = context.getSystemService((Context.NOTIFICATION_SERVICE)) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}