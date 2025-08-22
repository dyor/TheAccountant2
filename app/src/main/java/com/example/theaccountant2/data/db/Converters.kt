package com.example.theaccountant2.data.db

import androidx.room.TypeConverter
import com.example.theaccountant2.data.model.AccountType
import com.example.theaccountant2.data.model.NormalBalance

class Converters {
    @TypeConverter
    fun fromAccountType(value: AccountType?): String? {
        return value?.name
    }

    @TypeConverter
    fun toAccountType(value: String?): AccountType? {
        return value?.let { AccountType.valueOf(it) }
    }

    @TypeConverter
    fun fromNormalBalance(value: NormalBalance?): String? {
        return value?.name
    }

    @TypeConverter
    fun toNormalBalance(value: String?): NormalBalance? {
        return value?.let { NormalBalance.valueOf(it) }
    }
}
