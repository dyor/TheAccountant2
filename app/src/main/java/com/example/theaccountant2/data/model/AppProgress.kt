package com.example.theaccountant2.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Stores the user's current progress in the 52-day scenario script.
 * This table is expected to have only one row.
 */
@Entity(tableName = "app_progress")
data class AppProgress(
    @PrimaryKey
    val id: Int = 1, // Fixed ID to ensure only one row
    val currentDay: Int
)
