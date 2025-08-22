package com.example.theaccountant2.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.theaccountant2.data.model.AppProgress
import kotlinx.coroutines.flow.Flow

@Dao
interface AppProgressDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateProgress(appProgress: AppProgress)

    // Since we expect only one row with a fixed ID (e.g., 1)
    @Query("SELECT * FROM app_progress WHERE id = 1")
    fun getProgress(): Flow<AppProgress?>

    @Query("SELECT currentDay FROM app_progress WHERE id = 1")
    fun getCurrentDay(): Flow<Int?>

    // Helper to initialize if no progress exists
    @Query("SELECT COUNT(*) FROM app_progress WHERE id = 1")
    suspend fun hasProgressEntry(): Int
}
