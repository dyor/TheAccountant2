package com.example.theaccountant2.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * Represents a single line (debit or credit) within a journal entry.
 */
@Entity(
    tableName = "transactions",
    foreignKeys = [
        ForeignKey(
            entity = JournalEntry::class,
            parentColumns = ["id"],
            childColumns = ["entryId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Account::class,
            parentColumns = ["id"],
            childColumns = ["accountId"],
            onDelete = ForeignKey.RESTRICT // Prevent deleting an account if it has transactions
        )
    ],
    indices = [Index(value = ["entryId"]), Index(value = ["accountId"])]
)
data class Transaction(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val entryId: Int, // Foreign Key to JournalEntry
    val accountId: Int, // Foreign Key to Account
    val amount: Long, // Stored in cents
    val isDebit: Boolean
)
