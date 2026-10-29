
package com.example.bluepanda

import androidx.compose.runtime.mutableStateListOf

object NotificationLog {

    val messages = mutableStateListOf<String>()

    fun add(message: String) {
        messages.add(0, message)

        // Keep only the latest 100 entries
        if (messages.size > 100) {
            messages.removeAt(messages.lastIndex)
        }
    }
}
