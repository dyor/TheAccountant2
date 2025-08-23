package com.example.theaccountant2.data.model

import com.example.theaccountant2.data.model.Account // Assuming Account is in the same package or imported
import com.example.theaccountant2.data.model.EquityDetail // Assuming EquityDetail is in the same package or imported

data class BalanceSheetData(
    val assetAccounts: List<Account>,
    val totalAssets: Long,
    val liabilityAccounts: List<Account>,
    val totalLiabilities: Long,
    val equityDetail: EquityDetail,
    val totalEquity: Long,
    val balances: Boolean
)
