package com.example.theaccountant2.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.theaccountant2.data.model.Account
import com.example.theaccountant2.ui.viewmodel.BalanceSheetData
import com.example.theaccountant2.ui.viewmodel.FinancialStatementViewModel

@Composable
fun BalanceSheetScreen(
    modifier: Modifier = Modifier,
    viewModel: FinancialStatementViewModel
) {
    val balanceSheetData by viewModel.balanceSheetData.collectAsState()

    if (balanceSheetData == null) {
        Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Loading Balance Sheet data or no data available...")
        }
        return
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Text(
                "Balance Sheet",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                if (balanceSheetData!!.balances) "Status: Balanced" else "Status: Not Balanced!",
                style = MaterialTheme.typography.titleSmall,
                color = if (balanceSheetData!!.balances) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }

        // Assets Section
        item {
            StatementSection( // Reusing StatementSection from IncomeStatementScreen.kt for consistency
                title = "Assets",
                accounts = balanceSheetData!!.assetAccounts,
                totalAmount = balanceSheetData!!.totalAssets
            )
        }

        // Liabilities Section
        item {
            StatementSection(
                title = "Liabilities",
                accounts = balanceSheetData!!.liabilityAccounts,
                totalAmount = balanceSheetData!!.totalLiabilities
            )
        }

        // Equity Section
        item {
            EquitySectionCard(balanceSheetData = balanceSheetData!!)
        }

        // Total Liabilities and Equity
         item {
            Card(
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        "Total Liabilities & Equity",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        formatCurrency(balanceSheetData!!.totalLiabilities + balanceSheetData!!.totalEquity),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
fun EquitySectionCard(balanceSheetData: BalanceSheetData) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Equity", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

            balanceSheetData.equityDetail.beginningCapitalAccounts.forEach { account ->
                AccountRow(accountName = account.accountName, balance = account.balance)
            }
            // Display Total Beginning Capital if needed, or just individual accounts
            // AccountRow(accountName = "Total Beginning Capital", balance = balanceSheetData.equityDetail.totalBeginningCapital)

            AccountRow(accountName = "Net Income", balance = balanceSheetData.equityDetail.netIncome)

            if (balanceSheetData.equityDetail.drawingAccounts.isNotEmpty()) {
                 AccountRow(
                    accountName = "Owner's Drawings",
                    balance = balanceSheetData.equityDetail.totalDrawings,
                    isSubtraction = true
                )
            }

            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Total Equity", style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold)
                Text(formatCurrency(balanceSheetData.totalEquity), style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold)
            }
        }
    }
}
