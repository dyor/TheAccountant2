package com.example.theaccountant2

import android.app.Application
import com.example.theaccountant2.data.db.AppDatabase
import com.example.theaccountant2.data.repository.AppProgressRepository
import com.example.theaccountant2.data.repository.ScenarioRepository

class AccountantApp : Application() {

    // Lazy initialization for database and repositories
    val database: AppDatabase by lazy { AppDatabase.getDatabase(this) }

    val appProgressRepository: AppProgressRepository by lazy {
        AppProgressRepository(database.appProgressDao())
    }

    val scenarioRepository: ScenarioRepository by lazy {
        ScenarioRepository() // This is currently hardcoded, no DB dependency yet
    }

    override fun onCreate() {
        super.onCreate()
        // You can add any other app-wide initializations here if needed
    }
}
