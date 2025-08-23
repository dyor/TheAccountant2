package com.example.theaccountant2.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Represents the header for a journal entry.
 */
@Entity(tableName = "journal_entries")
data class JournalEntry(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val date: Long, // Timestamp
    val dayNumber: Int, // The game day number (1-364) for this entry
    val description: String
)

