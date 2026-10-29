
package com.example.bluepanda

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                var count by remember {
                    mutableIntStateOf(0)
                }

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Hello, Android!")

                    Text("Button clicks: $count")

                    Button(
                        onClick = { count++ }
                    ) {
                        Text("Click me")
                    }

                    Button(
                        onClick = {
                            startActivity(
                                Intent(
                                    Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS
                                )
                            )
                        }
                    ) {
                        Text("Enable Notification Access")
                    }

                    HorizontalDivider(
                        modifier = Modifier.padding(vertical = 12.dp)
                    )

                    Text(
                        text = "Captured Notifications",
                        style = MaterialTheme.typography.titleMedium
                    )

                    if (NotificationLog.messages.isEmpty()) {
                        Text(
                            text = "No notifications captured yet.",
                            modifier = Modifier.padding(12.dp)
                        )
                    } else {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            items(
                                items = NotificationLog.messages.toList()
                            ) { message ->
                                Text(
                                    text = message,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 4.dp)
                                )

                                HorizontalDivider()
                            }
                        }
                    }
                }
            }
        }
    }
}
