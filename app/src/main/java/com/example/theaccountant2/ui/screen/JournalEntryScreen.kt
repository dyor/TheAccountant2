package com.example.theaccountant2.ui.screen

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.theaccountant2.data.model.Account
import com.example.theaccountant2.ui.viewmodel.JournalEntryViewModel
import java.text.NumberFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JournalEntryScreen(
    modifier: Modifier = Modifier,
    viewModel: JournalEntryViewModel
) {
    val context = LocalContext.current

    val description by viewModel.description.collectAsState()
    val debitAccount by viewModel.debitAccount.collectAsState()
    val debitAmountString by viewModel.debitAmountString.collectAsState()
    val creditAccount by viewModel.creditAccount.collectAsState()
    val creditAmountString by viewModel.creditAmountString.collectAsState()
    val allAccounts by viewModel.allAccounts.collectAsState()

    // For formatting currency display
    val currencyFormat = NumberFormat.getCurrencyInstance(Locale.getDefault())

    // State for dropdown menus
    var debitDropdownExpanded by remember { mutableStateOf(false) }
    var creditDropdownExpanded by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text("Record Journal Entry", style = MaterialTheme.typography.headlineSmall)

        OutlinedTextField(
            value = description,
            onValueChange = { viewModel.onDescriptionChange(it) },
            label = { Text("Description") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
        )

        // Debit Account Dropdown
        ExposedDropdownMenuBox(
            expanded = debitDropdownExpanded,
            onExpandedChange = { debitDropdownExpanded = !debitDropdownExpanded },
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                value = debitAccount?.accountName ?: "Select Debit Account",
                onValueChange = {}, // Not editable directly
                readOnly = true,
                label = { Text("Debit Account") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = debitDropdownExpanded) },
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
            )
            ExposedDropdownMenu(
                expanded = debitDropdownExpanded,
                onDismissRequest = { debitDropdownExpanded = false }
            ) {
                allAccounts.forEach { account ->
                    DropdownMenuItem(
                        text = { Text(account.accountName) },
                        onClick = {
                            viewModel.onDebitAccountSelected(account)
                            debitDropdownExpanded = false
                        }
                    )
                }
            }
        }

        OutlinedTextField(
            value = debitAmountString,
            onValueChange = { viewModel.onDebitAmountChange(it) },
            label = { Text("Debit Amount") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next),
            singleLine = true
        )

        // Credit Account Dropdown
        ExposedDropdownMenuBox(
            expanded = creditDropdownExpanded,
            onExpandedChange = { creditDropdownExpanded = !creditDropdownExpanded },
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                value = creditAccount?.accountName ?: "Select Credit Account",
                onValueChange = {}, // Not editable
                readOnly = true,
                label = { Text("Credit Account") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = creditDropdownExpanded) },
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
            )
            ExposedDropdownMenu(
                expanded = creditDropdownExpanded,
                onDismissRequest = { creditDropdownExpanded = false }
            ) {
                allAccounts.forEach { account ->
                    DropdownMenuItem(
                        text = { Text(account.accountName) },
                        onClick = {
                            viewModel.onCreditAccountSelected(account)
                            creditDropdownExpanded = false
                        }
                    )
                }
            }
        }

        OutlinedTextField(
            value = creditAmountString,
            onValueChange = { viewModel.onCreditAmountChange(it) },
            label = { Text("Credit Amount") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Done),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Display Totals (simplified, as ViewModel ensures they are equal for now)
        val debitTotal = debitAmountString.toDoubleOrNull() ?: 0.0
        Text(
            "Debits: ${currencyFormat.format(debitTotal)} | Credits: ${currencyFormat.format(debitTotal)}",
            style = MaterialTheme.typography.bodyLarge
        )


        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {
                viewModel.postJournalEntry(
                    onSuccess = {
                        Toast.makeText(context, "Journal entry posted!", Toast.LENGTH_SHORT).show()
                        // Navigation back or to next step will be handled later
                    },
                    onError = { errorMessage ->
                        Toast.makeText(context, "Error: $errorMessage", Toast.LENGTH_LONG).show()
                    }
                )
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Post Journal Entry")
        }
    }
}
