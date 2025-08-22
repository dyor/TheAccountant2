package com.example.theaccountant2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.example.theaccountant2.ui.screen.ScenarioScreen // Changed import
import com.example.theaccountant2.ui.theme.TheAccountant2Theme
import com.example.theaccountant2.ui.viewmodel.ScenarioViewModel
import com.example.theaccountant2.ui.viewmodel.ViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Get the application instance to access repositories
        val accountantApp = application as AccountantApp
        val viewModelFactory = ViewModelFactory(
            appProgressRepository = accountantApp.appProgressRepository,
            scenarioRepository = accountantApp.scenarioRepository
        )
        val scenarioViewModel = ViewModelProvider(this, viewModelFactory)[ScenarioViewModel::class.java]

        setContent {
            TheAccountant2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // Replaced SampleScreen with ScenarioScreen
                    ScenarioScreen(
                        modifier = Modifier.padding(innerPadding),
                        scenarioViewModel = scenarioViewModel
                    )
                }
            }
        }
    }
}
