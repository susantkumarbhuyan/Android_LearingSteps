package com.zerocoder

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface QuoteDao {
    @Query("SELECT * from quote")
    fun getQuote(): LiveData<List<Quote>>

    @Insert
    suspend fun insertQuote(quote: Quote)
}