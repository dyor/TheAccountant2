package com.example.theaccountant2.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.navigation.NavHostController // IMPORT ADDED
import com.example.theaccountant2.data.model.Account
import com.example.theaccountant2.ui.viewmodel.FinancialStatementViewModel
//import com.example.theaccountant2.ui.viewmodel.IncomeStatementData
// Removed java.text.NumberFormat and java.util.Locale as they are now in the utility function
import com.example.theaccountant2.util.formatCurrency // Added import for shared utility
import com.example.theaccountant2.ui.common.AccountRow // Added import for shared AccountRow


@Composable
fun IncomeStatementScreen(
    modifier: Modifier = Modifier,
    viewModel: FinancialStatementViewModel,
    navController: NavHostController // PARAMETER ADDED
) {
    val incomeStatementData by viewModel.incomeStatementData.collectAsState()

    if (incomeStatementData == null) {
        Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Loading Income Statement data or no data available...")
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
                "Income Statement",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        // Revenues Section
        item {
            StatementSection(
                title = "Revenues",
                accounts = incomeStatementData!!.revenueAccounts,
                totalAmount = incomeStatementData!!.totalRevenue
            )
        }

        // Expenses Section
        item {
            StatementSection(
                title = "Expenses",
                accounts = incomeStatementData!!.expenseAccounts,
                totalAmount = incomeStatementData!!.totalExpenses,
                isExpense = true // Expenses reduce net income
            )
        }

        // Net Income Section
        item {
            NetIncomeSection(
                netIncome = incomeStatementData!!.netIncome,
                totalRevenue = incomeStatementData!!.totalRevenue,
                totalExpenses = incomeStatementData!!.totalExpenses
            )
        }
    }
}

@Composable
fun StatementSection(
    title: String,
    accounts: List<Account>,
    totalAmount: Long,
    isExpense: Boolean = false // To potentially style expenses differently if needed
) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(title, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
            if (accounts.isEmpty()) {
                Text("No ${title.lowercase()} recorded.", style = MaterialTheme.typography.bodyMedium)
            } else {
                accounts.forEach { account ->
                    AccountRow(accountName = account.accountName, balance = account.balance)
                }
            }
            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Total $title", style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold)
                Text(formatCurrency(totalAmount), style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun NetIncomeSection(netIncome: Long, totalRevenue: Long, totalExpenses: Long) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (netIncome >= 0) MaterialTheme.colorScheme.secondaryContainer
                             else MaterialTheme.colorScheme.errorContainer
        ),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Net Income Summary", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
            AccountRow("Total Revenue", totalRevenue)
            AccountRow("Total Expenses", totalExpenses, isSubtraction = true)
            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    if (netIncome >= 0) "Net Income" else "Net Loss",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    formatCurrency(netIncome),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = if (netIncome >= 0) Color.Unspecified else MaterialTheme.colorScheme.error
                )
            }
        }
    }
}

// AccountRow composable removed from here, will use the one from ui.common
