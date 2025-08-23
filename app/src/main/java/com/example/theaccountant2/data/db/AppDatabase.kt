package com.example.theaccountant2.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.theaccountant2.data.model.Account
import com.example.theaccountant2.data.model.AccountType
import com.example.theaccountant2.data.model.AppProgress
import com.example.theaccountant2.data.model.NormalBalance
import com.example.theaccountant2.data.model.JournalEntry
import com.example.theaccountant2.data.model.Transaction
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

@Database(
    entities = [Account::class, JournalEntry::class, Transaction::class, AppProgress::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun accountDao(): AccountDao
    abstract fun journalEntryDao(): JournalEntryDao
    abstract fun transactionDao(): TransactionDao
    abstract fun appProgressDao(): AppProgressDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        private val applicationScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "aaa_software_database"
                )
                .addCallback(AppDatabaseCallback(applicationScope))
                .build()
                INSTANCE = instance
                instance
            }
        }

        private fun getDefaultAccounts(): List<Account> {
            return listOf(
                // Assets
                Account(id = 0, accountNumber = "1010", accountName = "Cash", type = AccountType.ASSET, normalBalance = NormalBalance.DEBIT, balance = 0L),
                Account(id = 0, accountNumber = "1020", accountName = "Accounts Receivable", type = AccountType.ASSET, normalBalance = NormalBalance.DEBIT, balance = 0L),
                Account(id = 0, accountNumber = "1030", accountName = "Supplies", type = AccountType.ASSET, normalBalance = NormalBalance.DEBIT, balance = 0L),
                Account(id = 0, accountNumber = "1040", accountName = "Equipment", type = AccountType.ASSET, normalBalance = NormalBalance.DEBIT, balance = 0L),
                // Liabilities
                Account(id = 0, accountNumber = "2010", accountName = "Accounts Payable", type = AccountType.LIABILITY, normalBalance = NormalBalance.CREDIT, balance = 0L),
                Account(id = 0, accountNumber = "2020", accountName = "Unearned Revenue", type = AccountType.LIABILITY, normalBalance = NormalBalance.CREDIT, balance = 0L),
                // Equity
                Account(id = 0, accountNumber = "3010", accountName = "Owner's Capital", type = AccountType.EQUITY, normalBalance = NormalBalance.CREDIT, balance = 0L),
                Account(id = 0, accountNumber = "3020", accountName = "Owner's Drawings", type = AccountType.EQUITY, normalBalance = NormalBalance.DEBIT, balance = 0L),
                // Revenue
                Account(id = 0, accountNumber = "4010", accountName = "Service Revenue", type = AccountType.REVENUE, normalBalance = NormalBalance.CREDIT, balance = 0L),
                // Expenses
                Account(id = 0, accountNumber = "5010", accountName = "Rent Expense", type = AccountType.EXPENSE, normalBalance = NormalBalance.DEBIT, balance = 0L),
                Account(id = 0, accountNumber = "5020", accountName = "Utilities Expense", type = AccountType.EXPENSE, normalBalance = NormalBalance.DEBIT, balance = 0L),
                Account(id = 0, accountNumber = "5030", accountName = "Salaries Expense", type = AccountType.EXPENSE, normalBalance = NormalBalance.DEBIT, balance = 0L)
            )
        }

        private class AppDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                INSTANCE?.let { database ->
                    scope.launch {
                        // Initialize AppProgress
                        val appProgressDao = database.appProgressDao()
                        if (appProgressDao.hasProgressEntry() == 0) {
                            appProgressDao.updateProgress(AppProgress(currentDay = 1))
                        }

                        // Pre-populate Chart of Accounts
                        val accountDao = database.accountDao()
                        // Corrected line below:
                        accountDao.insertAllAccounts(getDefaultAccounts())
                    }
                }
            }
        }
    }
}
