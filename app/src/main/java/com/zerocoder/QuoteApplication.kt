package com.zerocoder

import android.app.Application
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.zerocoder.api.QuoteService
import com.zerocoder.api.RetrofirHelper
import com.zerocoder.db.QuoteDb
import com.zerocoder.repository.QuoteRepository
import com.zerocoder.work.QuoteWorker
import java.util.concurrent.TimeUnit

class QuoteApplication : Application() {

    lateinit var quoteRepository: QuoteRepository
    override fun onCreate() {
        super.onCreate()
        initilize()
        setWorker()
    }

    private fun setWorker() {
        val constraint = Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
        val workerRequest =
            PeriodicWorkRequest.Builder(QuoteWorker::class.java, 30, TimeUnit.MINUTES)
                .setConstraints(constraint).build()
        WorkManager.getInstance(this).enqueue(workerRequest)
    }

    private fun initilize() {
        val quoteService = RetrofirHelper.getInstance().create(QuoteService::class.java)
        val quoteDb = QuoteDb.getQuoteDB(applicationContext)
        quoteRepository = QuoteRepository(quoteService, quoteDb, applicationContext)
    }
}