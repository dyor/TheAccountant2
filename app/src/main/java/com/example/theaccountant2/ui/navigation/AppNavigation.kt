package com.example.theaccountant2.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext // Added import
import androidx.lifecycle.viewmodel.compose.viewModel // Added import
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.theaccountant2.AccountantApp // Added import
import com.example.theaccountant2.ui.screen.BalanceSheetScreen
import com.example.theaccountant2.ui.screen.IncomeStatementScreen
import com.example.theaccountant2.ui.screen.JournalEntryScreen
import com.example.theaccountant2.ui.screen.ScenarioScreen
import com.example.theaccountant2.ui.viewmodel.FinancialStatementViewModel // Added import
import com.example.theaccountant2.ui.viewmodel.JournalEntryViewModel // Added import
import com.example.theaccountant2.ui.viewmodel.ScenarioViewModel // Added import

@Composable
fun AppNavigation(navController: NavHostController) {
    val application = LocalContext.current.applicationContext as AccountantApp
    val factory = application.viewModelFactory

    NavHost(navController = navController, startDestination = Screen.Scenario.route) {
        composable(Screen.Scenario.route) {
            val scenarioViewModel: ScenarioViewModel = viewModel(factory = factory)
            val financialStatementViewModel: FinancialStatementViewModel = viewModel(factory = factory)
            ScenarioScreen(
                navController = navController,
                scenarioViewModel = scenarioViewModel,
                financialStatementViewModel = financialStatementViewModel
            )
        }
        composable(
            route = Screen.JournalEntry.route,
            arguments = listOf(navArgument("narrative") { type = NavType.StringType })
        ) { backStackEntry ->
            val narrative = backStackEntry.arguments?.getString("narrative") ?: ""
            val journalEntryViewModel: JournalEntryViewModel = viewModel(factory = factory)
            JournalEntryScreen(
                navController = navController,
                viewModel = journalEntryViewModel,
                narrative = narrative // Corrected to match JournalEntryScreen\'s parameter name
            )
        }
        composable(Screen.IncomeStatement.route) {
            val financialStatementViewModel: FinancialStatementViewModel = viewModel(factory = factory)
            IncomeStatementScreen(
                navController = navController,
                viewModel = financialStatementViewModel
            )
        }
        composable(Screen.BalanceSheet.route) {
            val financialStatementViewModel: FinancialStatementViewModel = viewModel(factory = factory)
            BalanceSheetScreen(
                navController = navController,
                viewModel = financialStatementViewModel
            )
        }
    }
}
