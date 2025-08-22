package com.example.theaccountant2.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.theaccountant2.data.model.Account
import com.example.theaccountant2.data.model.AppProgress
import com.example.theaccountant2.data.model.JournalEntry
import com.example.theaccountant2.data.model.Transaction
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

@Database(
    entities = [Account::class, JournalEntry::class, Transaction::class, AppProgress::class],
    version = 1,
    exportSchema = false // For simplicity in this project, we can disable schema exportation
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

        // CoroutineScope for the callback operations
        private val applicationScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "aaa_software_database"
                )
                .addCallback(AppDatabaseCallback(applicationScope)) // Added callback
                .build()
                INSTANCE = instance
                instance
            }
        }

        private class AppDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                INSTANCE?.let { database ->
                    scope.launch {
                        val appProgressDao = database.appProgressDao()
                        // Check if progress entry already exists (it shouldn't on create)
                        if (appProgressDao.hasProgressEntry() == 0) {
                            appProgressDao.updateProgress(AppProgress(currentDay = 1))
                        }
                        // Chart of Accounts pre-population will be added here in Phase 6
                    }
                }
            }
        }
    }
}
