package com.example.theaccountant2.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.theaccountant2.data.db.AccountDao
import com.example.theaccountant2.data.db.JournalEntryDao
import com.example.theaccountant2.data.db.TransactionDao
import com.example.theaccountant2.data.repository.AppProgressRepository
import com.example.theaccountant2.data.repository.ScenarioRepository

/**
 * ViewModelFactory for creating ViewModels that require repository and DAO dependencies.
 */
class ViewModelFactory(
    // Repositories
    private val appProgressRepository: AppProgressRepository,
    private val scenarioRepository: ScenarioRepository,
    // DAOs - Add DAOs needed by ViewModels created by this factory
    private val accountDao: AccountDao,
    private val journalEntryDao: JournalEntryDao,
    private val transactionDao: TransactionDao
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(ScenarioViewModel::class.java) -> {
                ScenarioViewModel(appProgressRepository, scenarioRepository) as T
            }
            modelClass.isAssignableFrom(JournalEntryViewModel::class.java) -> {
                JournalEntryViewModel(
                    accountDao,
                    journalEntryDao,
                    transactionDao,
                    appProgressRepository
                ) as T
            }
            modelClass.isAssignableFrom(FinancialStatementViewModel::class.java) -> { // Added this case
                FinancialStatementViewModel(accountDao) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }
}
