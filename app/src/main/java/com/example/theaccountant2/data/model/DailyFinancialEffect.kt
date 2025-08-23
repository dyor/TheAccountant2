package com.example.theaccountant2.data.model

data class DailyFinancialEffect(
    val dayNumber: Int,
    val amount: Long // Positive for net P&L increase, negative for net P&L decrease
)
