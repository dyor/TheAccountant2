package com.example.theaccountant2.ui.navigation

sealed class Screen(val route: String) {
    object Scenario : Screen("scenario_screen")
    object JournalEntry : Screen("journal_entry_screen")
    object IncomeStatement : Screen("income_statement_screen")
    object BalanceSheet : Screen("balance_sheet_screen")
    // Add other screens here as needed
}
