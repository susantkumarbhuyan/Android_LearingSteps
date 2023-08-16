package com.zerocoder.repository

import android.content.Context
import androidx.core.math.MathUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.zerocoder.NetworkUtil
import com.zerocoder.api.QuoteService
import com.zerocoder.db.QuoteDb
import com.zerocoder.model.QuoteList

import java.lang.Exception

class QuoteRepository(
    private val quoteService: QuoteService,
    private val quoteDb: QuoteDb,
    private val applicationContext: Context
) {
    private val quoteLiveData = MutableLiveData<BaseResponse<QuoteList>>()

    val quotes: LiveData<BaseResponse<QuoteList>>
        get() = quoteLiveData

    suspend fun getQuotes(page: Int) {
        if (NetworkUtil.isInternetAvailable(applicationContext)) {
            try {
                val result = quoteService.getQuotes(page)
                if (result.body() != null) {
                    quoteDb.quoteDao().addQuotes(result.body()!!.results)
                    quoteLiveData.postValue(BaseResponse.Success(result.body()))
                }
            } catch (e: Exception) {
                quoteLiveData.postValue(BaseResponse.Error(e.message.toString()))
            }


        } else {
            val quotes = quoteDb.quoteDao().getQuotes()
            val quoteList = QuoteList(1, 2, 2, quotes, 3, 4)
            quoteLiveData.postValue(BaseResponse.Success(quoteList))

        }
    }

    suspend fun getQuotesInBackGround() {
        val randomNumber: Int = (Math.random() * 10).toInt()
        val result = quoteService.getQuotes(randomNumber)
        if (result.body() != null) {
            quoteDb.quoteDao().addQuotes(result.body()!!.results)

        }
    }

}