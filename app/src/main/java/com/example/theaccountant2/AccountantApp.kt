package com.example.theaccountant2

import android.app.Application
import com.example.theaccountant2.data.db.AppDatabase
import com.example.theaccountant2.data.repository.AppProgressRepository
import com.example.theaccountant2.data.repository.ScenarioRepository
import com.example.theaccountant2.ui.viewmodel.ViewModelFactory // Added import

class AccountantApp : Application() {

    // Lazy initialization for database and repositories
    val database: AppDatabase by lazy { AppDatabase.getDatabase(this) }

    val appProgressRepository: AppProgressRepository by lazy {
        AppProgressRepository(database.appProgressDao())
    }

    val scenarioRepository: ScenarioRepository by lazy {
        ScenarioRepository() // This is currently hardcoded, no DB dependency yet
    }

    // ViewModelFactory, depends on DAOs and Repositories
    val viewModelFactory: ViewModelFactory by lazy { // Added viewModelFactory property
        ViewModelFactory(
            appProgressRepository = appProgressRepository,
            scenarioRepository = scenarioRepository,
            accountDao = database.accountDao(),
            journalEntryDao = database.journalEntryDao(),
            transactionDao = database.transactionDao()
        )
    }

    override fun onCreate() {
        super.onCreate()
        // You can add any other app-wide initializations here if needed
    }
}
