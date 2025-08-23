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
// import androidx.compose.ui.graphics.Color // Not used, can be removed
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.theaccountant2.data.model.Account
import com.example.theaccountant2.data.model.BalanceSheetData
import com.example.theaccountant2.ui.viewmodel.FinancialStatementViewModel
import com.example.theaccountant2.util.formatCurrency
import com.example.theaccountant2.ui.common.AccountRow // Added import for shared AccountRow


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

// Removed local private fun formatCurrency(value: Long) - This was already done
// StatementSection is still local to this file. It might be a candidate for sharing if used elsewhere.
@Composable
private fun StatementSection(title: String, accounts: List<Account>, totalAmount: Long) {
    Column(Modifier.padding(vertical = 8.dp)) {
        Text(text = title, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
        HorizontalDivider(Modifier.padding(vertical = 4.dp))
        accounts.forEach { account ->
            AccountRow(accountName = account.accountName, balance = account.balance) // This will now use the imported AccountRow
        }
        HorizontalDivider(Modifier.padding(vertical = 4.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Total $title", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            Text(text = formatCurrency(totalAmount), style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
        }
    }
}

// AccountRow composable removed from here, will use the one from ui.common
