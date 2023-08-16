package com.zerocoder.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.zerocoder.model.Result

@Dao
interface QuoteDao {
    @Insert
    suspend fun addQuotes(quotes: List<Result>)

    @Query("SELECT * from quote")
    suspend fun getQuotes(): List<Result>
}