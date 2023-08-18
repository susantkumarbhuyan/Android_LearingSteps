package com.zerocoder.work

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.zerocoder.QuoteApplication
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class QuoteWorker(private val context: Context, parameters: WorkerParameters) :
    Worker(context, parameters) {
    override fun doWork(): Result {

        CoroutineScope(Dispatchers.IO).launch {
//            repository.getQuotesInBackGround()
        }
        return Result.success()
    }
}