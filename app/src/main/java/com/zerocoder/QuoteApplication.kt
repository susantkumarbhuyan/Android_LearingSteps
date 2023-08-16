package com.zerocoder

import android.app.Application
import com.zerocoder.api.QuoteService
import com.zerocoder.api.RetrofirHelper
import com.zerocoder.db.QuoteDb
import com.zerocoder.repository.QuoteRepository

class QuoteApplication : Application() {

    lateinit var quoteRepository: QuoteRepository
    override fun onCreate() {
        super.onCreate()
        initilize()
    }

    private fun initilize() {
        val quoteService = RetrofirHelper.getInstance().create(QuoteService::class.java)
        val quoteDb = QuoteDb.getQuoteDB(applicationContext)
        quoteRepository = QuoteRepository(quoteService, quoteDb, applicationContext)
    }
}