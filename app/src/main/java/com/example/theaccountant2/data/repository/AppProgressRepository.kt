package com.example.theaccountant2.data.repository

import com.example.theaccountant2.data.db.AppProgressDao
import com.example.theaccountant2.data.model.AppProgress
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged

/**
 * Repository for managing AppProgress data.
 */
class AppProgressRepository(private val appProgressDao: AppProgressDao) {

    /**
     * Provides a flow of the current day. Emits null if no progress is set.
     * distinctUntilChanged ensures that we only get updates when the day actually changes.
     */
    val currentDay: Flow<Int?> = appProgressDao.getCurrentDay().distinctUntilChanged()

    /**
     * Updates the current day in the database.
     */
    suspend fun updateCurrentDay(day: Int) {
        appProgressDao.updateProgress(AppProgress(currentDay = day))
    }

    /**
     * Ensures that there is an initial progress entry. If not, creates one starting at day 1.
     * This is a safeguard, as the Database Callback should handle the very first initialization.
     */
    suspend fun ensureInitialProgress() {
        if (appProgressDao.hasProgressEntry() == 0) {
            appProgressDao.updateProgress(AppProgress(currentDay = 1))
        }
    }
}
