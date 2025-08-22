package com.example.theaccountant2.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Represents an account in the Chart of Accounts.
 */
@Entity(tableName = "accounts")
data class Account(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val accountNumber: String,
    val accountName: String,
    val type: AccountType,
    val normalBalance: NormalBalance,
    var balance: Long // Stored in cents
)
