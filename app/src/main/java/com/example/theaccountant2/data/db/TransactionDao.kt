package com.example.theaccountant2.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
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
}
