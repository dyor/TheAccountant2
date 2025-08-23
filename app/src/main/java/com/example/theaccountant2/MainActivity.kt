package com.example.theaccountant2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.theaccountant2.ui.navigation.Screen
// Import new screens
import com.example.theaccountant2.ui.screen.BalanceSheetScreen
import com.example.theaccountant2.ui.screen.IncomeStatementScreen
import com.example.theaccountant2.ui.screen.JournalEntryScreen
import com.example.theaccountant2.ui.screen.ScenarioScreen
import com.example.theaccountant2.ui.theme.TheAccountant2Theme
// Import new ViewModel
import com.example.theaccountant2.ui.viewmodel.FinancialStatementViewModel
import com.example.theaccountant2.ui.viewmodel.JournalEntryViewModel
import com.example.theaccountant2.ui.viewmodel.ScenarioViewModel
import com.example.theaccountant2.ui.viewmodel.ViewModelFactory
import kotlinx.coroutines.flow.collectLatest

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val accountantApp = application as AccountantApp
        val viewModelFactory = ViewModelFactory(
            appProgressRepository = accountantApp.appProgressRepository,
            scenarioRepository = accountantApp.scenarioRepository,
            accountDao = accountantApp.database.accountDao(),
            journalEntryDao = accountantApp.database.journalEntryDao(),
            transactionDao = accountantApp.database.transactionDao()
        )

        setContent {
            TheAccountant2Theme {
                val navController = rememberNavController()
                val scenarioViewModel = ViewModelProvider(this, viewModelFactory)[ScenarioViewModel::class.java]
                val financialStatementViewModel = ViewModelProvider(this, viewModelFactory)[FinancialStatementViewModel::class.java]

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = Screen.Scenario.route,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable(Screen.Scenario.route) {
                            LaunchedEffect(Unit) {
                                scenarioViewModel.navigateToJournalEntry.collectLatest {
                                    navController.navigate(Screen.JournalEntry.route)
                                }
                            }
                            // Added LaunchedEffects for navigating to financial statements
                            LaunchedEffect(Unit) {
                                scenarioViewModel.navigateToIncomeStatement.collectLatest {
                                    navController.navigate(Screen.IncomeStatement.route)
                                }
                            }
                            LaunchedEffect(Unit) {
                                scenarioViewModel.navigateToBalanceSheet.collectLatest {
                                    navController.navigate(Screen.BalanceSheet.route)
                                }
                            }

                            ScenarioScreen(
                                scenarioViewModel = scenarioViewModel
                            )
                        }
                        composable(Screen.JournalEntry.route) {
                            val journalEntryViewModel = ViewModelProvider(
                                owner = it,
                                factory = viewModelFactory
                            )[JournalEntryViewModel::class.java]

                            JournalEntryScreen(
                                viewModel = journalEntryViewModel
                            )

                            LaunchedEffect(Unit) {
                                journalEntryViewModel.navigateBackToScenario.collectLatest {
                                    navController.popBackStack()
                                }
                            }
                        }
                        composable(Screen.IncomeStatement.route) {
                            IncomeStatementScreen(viewModel = financialStatementViewModel)
                        }
                        composable(Screen.BalanceSheet.route) {
                            BalanceSheetScreen(viewModel = financialStatementViewModel)
                        }
                    }
                }
            }
        }
    }
}
