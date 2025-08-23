package com.example.theaccountant2.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.theaccountant2.data.db.AccountDao // Still needed for other statements
import com.example.theaccountant2.data.db.TransactionDao
// Import JournalEntryDao if still needed for other financial statements, otherwise remove
// import com.example.theaccountant2.data.db.JournalEntryDao
import com.example.theaccountant2.data.model.Account
import com.example.theaccountant2.data.model.AccountType
import com.example.theaccountant2.data.model.DailyFinancialEffect // Used by the DAO
import com.example.theaccountant2.data.model.IncomeStatementData
import com.example.theaccountant2.data.model.BalanceSheetData
import com.example.theaccountant2.data.model.EquityDetail
import com.example.theaccountant2.data.model.WeeklyPnlData
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.combine // Still needed for BalanceSheet

class FinancialStatementViewModel(
    private val accountDao: AccountDao,
    // private val journalEntryDao: JournalEntryDao, // Potentially no longer needed here if only for P&L chart
    private val transactionDao: TransactionDao
) : ViewModel() {

    private val allAccounts: StateFlow<List<Account>> = accountDao.getAllAccounts()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    // Removed: allJournalEntries, allTransactions (unless needed by other statements not yet refactored)
    // Removed: accountMapFlow, journalEntryDayMapFlow

    val incomeStatementData: StateFlow<IncomeStatementData?> = allAccounts.map { accounts ->
        if (accounts.isEmpty()) return@map null
        val revenueAccounts = accounts.filter { it.type == AccountType.REVENUE }
        val totalRevenue = revenueAccounts.sumOf { it.balance }
        val expenseAccounts = accounts.filter { it.type == AccountType.EXPENSE }
        val totalExpenses = expenseAccounts.sumOf { it.balance }
        val netIncome = totalRevenue - totalExpenses
        IncomeStatementData(
            revenueAccounts = revenueAccounts,
            totalRevenue = totalRevenue,
            expenseAccounts = expenseAccounts,
            totalExpenses = totalExpenses,
            netIncome = netIncome
        )
    }.stateIn(viewModelScope, SharingStarted.Lazily, null)

    val balanceSheetData: StateFlow<BalanceSheetData?> =
        combine(allAccounts, incomeStatementData) { accounts, incomeData ->
            if (accounts.isEmpty()) return@combine null
            val netIncomeForPeriod = incomeData?.netIncome ?: 0L
            val assetAccounts = accounts.filter { it.type == AccountType.ASSET }
            val totalAssets = assetAccounts.sumOf { it.balance }
            val liabilityAccounts = accounts.filter { it.type == AccountType.LIABILITY }
            val totalLiabilities = liabilityAccounts.sumOf { it.balance }
            val capitalAccounts = accounts.filter {
                it.type == AccountType.EQUITY && !it.accountName.contains("Drawings", ignoreCase = true)
            }
            val totalBeginningCapital = capitalAccounts.sumOf { it.balance }
            val drawingAccounts = accounts.filter {
                it.type == AccountType.EQUITY && it.accountName.contains("Drawings", ignoreCase = true)
            }
            val totalDrawings = drawingAccounts.sumOf { it.balance }
            val calculatedTotalEquity = totalBeginningCapital - totalDrawings + netIncomeForPeriod
            val equityDetail = EquityDetail(
                beginningCapitalAccounts = capitalAccounts,
                totalBeginningCapital = totalBeginningCapital,
                drawingAccounts = drawingAccounts,
                totalDrawings = totalDrawings,
                netIncome = netIncomeForPeriod,
                endingTotalEquity = calculatedTotalEquity
            )
            BalanceSheetData(
                assetAccounts = assetAccounts,
                totalAssets = totalAssets,
                liabilityAccounts = liabilityAccounts,
                totalLiabilities = totalLiabilities,
                equityDetail = equityDetail,
                totalEquity = calculatedTotalEquity,
                balances = (totalAssets == (totalLiabilities + calculatedTotalEquity))
            )
        }.stateIn(viewModelScope, SharingStarted.Lazily, null)

    val weeklyProfitAndLossData: StateFlow<List<WeeklyPnlData>> =
        transactionDao.getDailyFinancialEffects(
            revenueType = AccountType.REVENUE.name, // Pass enum name as String
            expenseType = AccountType.EXPENSE.name   // Pass enum name as String
        )
        .map { dailyEffects ->
            val weeklyPnlArray = Array(52) { WeeklyPnlData(it + 1, 0L, false) }
            if (dailyEffects.isEmpty()) { // Handle case where there's no P&L activity yet
                return@map weeklyPnlArray.toList()
            }
            dailyEffects.forEach { dailyEffect ->
                // Ensure dayNumber (simulated week) is within a valid range (1-52)
                if (dailyEffect.dayNumber <= 0 || dailyEffect.dayNumber > 52) {
                    Log.w("FinancialStatementVM", "Invalid simulated week number ${dailyEffect.dayNumber} received from DAO.")
                    return@forEach // Skip this invalid entry
                }
                val weekIndex = dailyEffect.dayNumber - 1 // dayNumber is 1-based, weekIndex is 0-based
                
                // weekIndex should be 0-51
                // This additional check handles potential issues if dayNumber was valid (1-52)
                // but somehow weekIndex calculation led to an out-of-bounds value.
                // Given the current calculation (dayNumber - 1), this should only fail if dayNumber was not 1-52,
                // which is caught by the check above. However, keeping it provides an extra safety net.
                if (weekIndex >= 0 && weekIndex < 52) {
                    val currentWeekData = weeklyPnlArray[weekIndex]
                    weeklyPnlArray[weekIndex] = currentWeekData.copy(
                        profitLoss = currentWeekData.profitLoss + dailyEffect.amount,
                        hasActivity = currentWeekData.hasActivity || dailyEffect.amount != 0L // Mark as having activity
                    )
                } else {
                     // This condition should ideally not be met if the above dayNumber check is correct.
                    Log.e("FinancialStatementVM", "Simulated week number ${dailyEffect.dayNumber} resulted in invalid weekIndex $weekIndex after calculation.")
                }
            }
            weeklyPnlArray.toList()
        }
        .stateIn(viewModelScope, SharingStarted.Lazily, List(52) { i -> WeeklyPnlData(i + 1, 0L, false) })
}