package com.example.theaccountant2.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.theaccountant2.data.db.AccountDao
import com.example.theaccountant2.data.model.Account
import com.example.theaccountant2.data.model.AccountType
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

// Data classes to hold structured statement data (can be expanded)
data class IncomeStatementData(
    val revenueAccounts: List<Account>,
    val totalRevenue: Long,
    val expenseAccounts: List<Account>,
    val totalExpenses: Long,
    val netIncome: Long
)

data class BalanceSheetData(
    val assetAccounts: List<Account>,
    val totalAssets: Long,
    val liabilityAccounts: List<Account>,
    val totalLiabilities: Long,
    val equityDetail: EquityDetail, // Using a new data class for clarity
    val totalEquity: Long,
    val balances: Boolean // To check if Assets = Liabilities + Equity
)

// New data class for detailed equity components
data class EquityDetail(
    val beginningCapitalAccounts: List<Account>, // e.g. Owner's Capital
    val totalBeginningCapital: Long,
    val drawingAccounts: List<Account>,
    val totalDrawings: Long,
    val netIncome: Long, // For the period
    val endingTotalEquity: Long
)


class FinancialStatementViewModel(
    private val accountDao: AccountDao
) : ViewModel() {

    private val allAccounts: StateFlow<List<Account>> = accountDao.getAllAccounts()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    val incomeStatementData: StateFlow<IncomeStatementData?> = allAccounts.map {  accounts ->
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

    // Combined flow for Balance Sheet data that depends on Income Statement data (for Net Income)
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
}
