package com.example.theaccountant2

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize // Keep if used by MainAppScaffold or its children
import androidx.compose.material3.MaterialTheme // Keep
import androidx.compose.material3.Surface // Keep
// Remove Text, Composable, Modifier, Preview for Greeting if not used directly here
// import androidx.compose.material3.Text
// import androidx.compose.runtime.Composable
// import androidx.compose.ui.Modifier
// import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
// import com.example.theaccountant2.ui.navigation.AppNavigation // This will now be called by MainAppScaffold
import com.example.theaccountant2.ui.navigation.MainAppScaffold // **** CHANGED IMPORT ****
import com.example.theaccountant2.ui.theme.TheAccountant2Theme
import com.example.theaccountant2.util.AlarmScheduler
import com.example.theaccountant2.util.NotificationUtils

class MainActivity : ComponentActivity() {

    private lateinit var requestPermissionLauncher: ActivityResultLauncher<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestPermissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                // Permission is granted.
            } else {
                // Handle permission denial.
            }
        }

        NotificationUtils.createNotificationChannel(this)
        AlarmScheduler.scheduleDailyAlarm(this)
        askForNotificationPermission()

        setContent {
            TheAccountant2Theme {
                Surface( // Surface can remain if it\'s part of your overall theme/background
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainAppScaffold() // **** USE MainAppScaffold HERE ****
                }
            }
        }
    }

    private fun askForNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            when {
                ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                ) == PackageManager.PERMISSION_GRANTED -> {
                    // Permission is already granted
                }
                shouldShowRequestPermissionRationale(Manifest.permission.POST_NOTIFICATIONS) -> {
                    requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                }
                else -> {
                    requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                }
            }
        }
    }
}

// The Greeting composable and its Preview can be removed from MainActivity if no longer needed here,
// or if they were just for testing.
// @Composable
// fun Greeting(name: String, modifier: Modifier = Modifier) {
// Text(
// text = "Hello $name!",
// modifier = modifier
// )
// }
//
// @Preview(showBackground = true)
// @Composable
// fun GreetingPreview() {
// TheAccountant2Theme {
// Greeting("Android")
// }
// }
