package com.example.theaccountant2.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.theaccountant2.util.formatCurrency // Import the shared formatter

@Composable
fun AccountRow(accountName: String, balance: Long, isSubtraction: Boolean = false) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp), // Standardized padding
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = if (isSubtraction) "$accountName (Less)" else accountName,
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = formatCurrency(balance),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}
