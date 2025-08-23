package com.example.theaccountant2.data.model

import com.example.theaccountant2.data.model.Account // Assuming Account is in the same package or imported

data class IncomeStatementData(
    val revenueAccounts: List<Account>,
    val totalRevenue: Long,
    val expenseAccounts: List<Account>,
    val totalExpenses: Long,
    val netIncome: Long
)
