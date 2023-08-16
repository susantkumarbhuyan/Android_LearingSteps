package com.zerocoder.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.zerocoder.NetworkUtil
import com.zerocoder.api.QuoteService
import com.zerocoder.db.QuoteDb
import com.zerocoder.model.QuoteList

class QuoteRepository(
    private val quoteService: QuoteService,
    private val quoteDb: QuoteDb,
    private val applicationContext: Context
) {
    private val quoteLiveData = MutableLiveData<QuoteList>()

    val quotes: LiveData<QuoteList>
        get() = quoteLiveData

    suspend fun getQuotes(page: Int) {
        if (NetworkUtil.isInternetAvailable(applicationContext)) {
            val result = quoteService.getQuotes(page)
            if (result.body() != null) {
                quoteDb.quoteDao().addQuotes(result.body()!!.results)
                quoteLiveData.postValue(result.body())
            }
        } else {
            val quotes = quoteDb.quoteDao().getQuotes()
            val quoteList = QuoteList(1, 2, 2, quotes, 3, 4)
            quoteLiveData.postValue(quoteList)

        }


    }

}