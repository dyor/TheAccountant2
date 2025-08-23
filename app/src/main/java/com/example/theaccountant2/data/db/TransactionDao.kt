package com.example.theaccountant2.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.theaccountant2.data.model.DailyFinancialEffect // New import
import com.example.theaccountant2.data.model.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransaction(transaction: Transaction): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllTransactions(transactions: List<Transaction>)

    @Query("SELECT * FROM transactions WHERE entryId = :entryId ORDER BY id ASC")
    fun getTransactionsForEntry(entryId: Int): Flow<List<Transaction>>

    @Query("SELECT * FROM transactions WHERE accountId = :accountId ORDER BY id ASC")
    fun getTransactionsForAccount(accountId: Int): Flow<List<Transaction>>

    // Method to get all transactions (was already here)
    @Query("SELECT * FROM transactions")
    fun getAllTransactions(): Flow<List<Transaction>>

    // New method to get daily financial effects for P&L
    @Query("""
        SELECT
            je.dayNumber,
            SUM(
                CASE
                    WHEN acc.type = :revenueType THEN
                        CASE WHEN t.isDebit = 1 THEN -t.amount ELSE t.amount END 
                    WHEN acc.type = :expenseType THEN
                        CASE WHEN t.isDebit = 1 THEN -t.amount ELSE t.amount END
                    ELSE 0
                END
            ) as amount
        FROM transactions t
        INNER JOIN accounts acc ON t.accountId = acc.id
        INNER JOIN journal_entries je ON t.entryId = je.id
        WHERE acc.type IN (:revenueType, :expenseType)
        GROUP BY je.dayNumber
        ORDER BY je.dayNumber ASC
    """)
    fun getDailyFinancialEffects(revenueType: String, expenseType: String): Flow<List<DailyFinancialEffect>>
}
