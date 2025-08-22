package com.example.theaccountant2.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.theaccountant2.data.model.JournalEntry
import kotlinx.coroutines.flow.Flow

@Dao
interface JournalEntryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertJournalEntry(journalEntry: JournalEntry): Long // Returns the new rowId

    @Query("SELECT * FROM journal_entries WHERE id = :entryId")
    fun getJournalEntryById(entryId: Int): Flow<JournalEntry?>

    @Query("SELECT * FROM journal_entries ORDER BY date DESC")
    fun getAllJournalEntries(): Flow<List<JournalEntry>>
}
