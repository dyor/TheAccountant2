package com.example.theaccountant2.ui.screen

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.theaccountant2.R

const val TEST_CHANNEL_ID = "test_channel"
const val TEST_NOTIFICATION_ID = 1

fun showTestNotification(context: Context) {
    val notificationManager = NotificationManagerCompat.from(context)

    // TODO: Add runtime permission check for POST_NOTIFICATIONS for Android 13+
    // <uses-permission android:name=\"android.permission.POST_NOTIFICATIONS\"/> should be in AndroidManifest.xml

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val channel = NotificationChannel(
            TEST_CHANNEL_ID,
            "Test Notifications", // User-visible name
            NotificationManager.IMPORTANCE_DEFAULT
        ).apply {
            description = "Channel for test notifications" // User-visible description
        }
        val notificationManagerService =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManagerService.createNotificationChannel(channel)
    }

    val builder = NotificationCompat.Builder(context, TEST_CHANNEL_ID)
        .setSmallIcon(android.R.drawable.ic_dialog_info) // Replace with your app's icon
        .setContentTitle(context.getString(R.string.notification_test_title))
        .setContentText(context.getString(R.string.notification_test_text))
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)

    try {
        notificationManager.notify(TEST_NOTIFICATION_ID, builder.build())
        Toast.makeText(context, "Test notification sent!", Toast.LENGTH_SHORT).show()
    } catch (e: SecurityException) {
        Toast.makeText(context, "Notification permission missing. Check Logcat.", Toast.LENGTH_LONG).show()
        // Log.e("SettingsScreen", "Notification permission missing or other security issue", e)
        // Consider guiding the user to settings or handling the permission request here.
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    // navController: NavController // Will be needed later for navigating within settings
) {
    val context = LocalContext.current
    var enable8amNotification by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(id = R.string.screen_title_settings)) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues) // Apply Scaffold's padding first
                .padding(all = 16.dp),   // Then apply additional padding
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            Button(
                onClick = {
                    // TODO: Show confirmation dialog
                    // TODO: If confirmed, clear all game data
                    Toast.makeText(context, "Restart Game clicked (Not implemented)", Toast.LENGTH_SHORT).show()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(id = R.string.setting_restart_game))
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(id = R.string.setting_enable_8am_notification),
                    modifier = Modifier.weight(1f)
                )
                Switch(
                    checked = enable8amNotification,
                    onCheckedChange = { isChecked ->
                        enable8amNotification = isChecked
                        // TODO: Save this preference
                        // TODO: If checked, schedule 8 AM notification
                        // TODO: If unchecked, cancel 8 AM notification
                        val action = if (isChecked) "Enabled" else "Disabled"
                        Toast.makeText(context, "8 AM Notification $action (Not fully implemented)", Toast.LENGTH_SHORT).show()
                    }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    showTestNotification(context)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(id = R.string.setting_test_notification_now))
            }
        }
    }
}
