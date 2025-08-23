package com.example.theaccountant2.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.theaccountant2.data.db.AccountDao
import com.example.theaccountant2.data.db.JournalEntryDao
import com.example.theaccountant2.data.db.TransactionDao
import com.example.theaccountant2.data.model.Account
import com.example.theaccountant2.data.model.JournalEntry
import com.example.theaccountant2.data.model.NormalBalance
import com.example.theaccountant2.data.model.Transaction
import com.example.theaccountant2.data.repository.AppProgressRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.Date

class JournalEntryViewModel(
    private val accountDao: AccountDao,
    private val journalEntryDao: JournalEntryDao,
    private val transactionDao: TransactionDao,
    private val appProgressRepository: AppProgressRepository
) : ViewModel() {

    private val _description = MutableStateFlow("")
    val description: StateFlow<String> = _description.asStateFlow()

    private val _debitAccount = MutableStateFlow<Account?>(null)
    val debitAccount: StateFlow<Account?> = _debitAccount.asStateFlow()

    private val _debitAmountString = MutableStateFlow("")
    val debitAmountString: StateFlow<String> = _debitAmountString.asStateFlow()

    private val _creditAccount = MutableStateFlow<Account?>(null)
    val creditAccount: StateFlow<Account?> = _creditAccount.asStateFlow()

    private val _creditAmountString = MutableStateFlow("")
    val creditAmountString: StateFlow<String> = _creditAmountString.asStateFlow()

    val allAccounts: StateFlow<List<Account>> = accountDao.getAllAccounts()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    private val _navigateBackToScenario = MutableSharedFlow<Unit>()
    val navigateBackToScenario: SharedFlow<Unit> = _navigateBackToScenario.asSharedFlow()

    fun onDescriptionChange(newDescription: String) {
        _description.value = newDescription
    }

    fun onDebitAccountSelected(account: Account?) {
        _debitAccount.value = account
    }

    fun onDebitAmountChange(newAmount: String) {
        _debitAmountString.value = newAmount
        if (_creditAmountString.value.isBlank() || _creditAmountString.value != newAmount) {
             _creditAmountString.value = newAmount
        }
    }

    fun onCreditAccountSelected(account: Account?) {
        _creditAccount.value = account
    }

    fun onCreditAmountChange(newAmount: String) {
        _creditAmountString.value = newAmount
        if (_debitAmountString.value.isBlank() || _debitAmountString.value != newAmount) {
             _debitAmountString.value = newAmount
        }
    }

    private fun validateEntry(): Boolean {
        val debitAmount = _debitAmountString.value.toDoubleOrNull()?.times(100)?.toLong()
        val creditAmount = _creditAmountString.value.toDoubleOrNull()?.times(100)?.toLong()

        return _description.value.isNotBlank() &&
               _debitAccount.value != null &&
               _creditAccount.value != null &&
               debitAmount != null && debitAmount > 0 &&
               creditAmount != null && creditAmount > 0 &&
               debitAmount == creditAmount &&
               _debitAccount.value?.id != _creditAccount.value?.id
    }

    fun postJournalEntry(onSuccess: () -> Unit, onError: (String) -> Unit) {
        if (!validateEntry()) {
            onError("Invalid journal entry. Please check all fields. Debits must equal credits.")
            return
        }

        viewModelScope.launch {
            try {
                val entryDescription = _description.value
                val selectedDebitAccount = _debitAccount.value!!
                val selectedCreditAccount = _creditAccount.value!!
                val amount = _debitAmountString.value.toDoubleOrNull()?.times(100)?.toLong()
                    ?: run {
                        onError("Invalid debit amount format.")
                        return@launch
                    }

                // Get the current day for this journal entry
                val dayForEntry = appProgressRepository.currentDay.first()
                if (dayForEntry == null) {
                    onError("Critical error: Current day is not set. Cannot post journal entry.")
                    return@launch
                }

                val journalEntry = JournalEntry(
                    date = Date().time,
                    dayNumber = dayForEntry, // Assign the fetched day number
                    description = entryDescription
                )
                val entryId = journalEntryDao.insertJournalEntry(journalEntry)

                val debitTransaction = Transaction(
                    entryId = entryId,
                    accountId = selectedDebitAccount.id,
                    amount = amount,
                    isDebit = true
                )
                val creditTransaction = Transaction(
                    entryId = entryId,
                    accountId = selectedCreditAccount.id,
                    amount = amount,
                    isDebit = false
                )
                transactionDao.insertAllTransactions(listOf(debitTransaction, creditTransaction))

                val newDebitBalance = if (selectedDebitAccount.normalBalance == NormalBalance.DEBIT) {
                    selectedDebitAccount.balance + amount
                } else {
                    selectedDebitAccount.balance - amount
                }
                accountDao.updateBalance(selectedDebitAccount.id, newDebitBalance)

                val newCreditBalance = if (selectedCreditAccount.normalBalance == NormalBalance.CREDIT) {
                    selectedCreditAccount.balance + amount
                } else {
                    selectedCreditAccount.balance - amount
                }
                accountDao.updateBalance(selectedCreditAccount.id, newCreditBalance)

                // Advance day using the day number that was used for this entry
                appProgressRepository.updateCurrentDay(dayForEntry + 1)

                clearInputFields()
                onSuccess()
                _navigateBackToScenario.emit(Unit)

            } catch (e: Exception) {
                onError("Error posting journal entry: ${e.message}")
            }
        }
    }
    
    private fun clearInputFields() {
        _description.value = ""
        _debitAccount.value = null
        _debitAmountString.value = ""
        _creditAccount.value = null
        _creditAmountString.value = ""
    }
}
