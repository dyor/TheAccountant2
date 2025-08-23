package com.example.theaccountant2.ui.navigation

import java.net.URLEncoder
import java.nio.charset.StandardCharsets

sealed class Screen(val route: String) {
    object Scenario : Screen("scenario_screen")
    object JournalEntry : Screen("journal_entry_screen/{narrative}") {
        fun createRoute(narrative: String): String {
            val encodedNarrative = URLEncoder.encode(narrative, StandardCharsets.UTF_8.toString())
            return "journal_entry_screen/$encodedNarrative"
        }
    }
    object IncomeStatement : Screen("income_statement_screen")
    object BalanceSheet : Screen("balance_sheet_screen")
    // Add other screens here as needed
}
