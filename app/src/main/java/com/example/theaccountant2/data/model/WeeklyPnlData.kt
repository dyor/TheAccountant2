package com.example.theaccountant2.data.model

data class WeeklyPnlData(
    val weekNumber: Int, // 1-52
    val profitLoss: Long,
    val hasActivity: Boolean
)
