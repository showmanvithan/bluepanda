
package com.example.bluepanda

import android.app.Notification
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log

class NotificationListener : NotificationListenerService() {


    override fun onNotificationPosted(sbn: StatusBarNotification) {
        val extras = sbn.notification.extras
        val appPackage = sbn.packageName

        val title = extras.getCharSequence(
            Notification.EXTRA_TITLE
        )?.toString().orEmpty()

        val individualMessages =
            extras.getParcelableArray(Notification.EXTRA_MESSAGES)

        val messageText = individualMessages
            ?.mapNotNull { item ->
                val bundle = item as? android.os.Bundle
                val text = bundle
                    ?.getCharSequence("text")
                    ?.toString()

                text?.takeIf { it.isNotBlank() }
            }
            ?.distinct()
            ?.joinToString("\n")
            ?.takeIf { it.isNotBlank() }

        val text = messageText
            ?: extras.getCharSequence(Notification.EXTRA_BIG_TEXT)
                ?.toString()
            ?: extras.getCharSequence(Notification.EXTRA_TEXT)
                ?.toString()
            ?: "No message text available"

        val message = """
        App: $appPackage
        Title: $title
        Message: $text
    """.trimIndent()

        Log.d("BluePandaNotifications", message)
        NotificationLog.add(message)
    }

}
