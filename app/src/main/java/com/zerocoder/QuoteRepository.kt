package com.zerocoder

import androidx.lifecycle.LiveData

class QuoteRepository(private val quoteDao: QuoteDao) {
    fun getQuote(): LiveData<List<Quote>> {
        return quoteDao.getQuote()
    }

    suspend fun insertQuote(quote: Quote) {
        quoteDao.insertQuote(quote)
    }
}