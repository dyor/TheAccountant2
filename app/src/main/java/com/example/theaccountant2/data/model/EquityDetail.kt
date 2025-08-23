package com.example.theaccountant2.data.model

import com.example.theaccountant2.data.model.Account // Assuming Account is in the same package or imported

data class EquityDetail(
    val beginningCapitalAccounts: List<Account>,
    val totalBeginningCapital: Long,
    val drawingAccounts: List<Account>,
    val totalDrawings: Long,
    val netIncome: Long,
    val endingTotalEquity: Long
)
