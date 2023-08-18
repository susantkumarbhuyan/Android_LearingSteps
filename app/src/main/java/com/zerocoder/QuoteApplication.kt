package com.zerocoder

import android.app.Application
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.zerocoder.work.QuoteWorker
import dagger.hilt.android.HiltAndroidApp
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltAndroidApp
class QuoteApplication : Application() {

    @Inject
    lateinit var userRepository: UserRepository
    override fun onCreate() {
        super.onCreate()
        setWorker()
        userRepository.saveUser("Susant", "Sisant")
    }

    private fun setWorker() {
        val constraint = Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
        val workerRequest =
            PeriodicWorkRequest.Builder(QuoteWorker::class.java, 30, TimeUnit.MINUTES)
                .setConstraints(constraint).build()
        WorkManager.getInstance(this).enqueue(workerRequest)
    }

}