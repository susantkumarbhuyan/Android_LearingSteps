package com.zerocoder.api

import com.google.gson.Gson
import com.zerocoder.db.QuoteDao
import com.zerocoder.db.QuoteDb
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RetrofitHelper {

    private val BASE_URL = "https://api.quotable.io/"

    @Singleton // Tell Dagger-Hilt to create a singleton accessible everywhere in ApplicationCompenent (i.e. everywhere in the application)
    @Provides
    fun getInstance(): Retrofit =
        Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
    @Provides
    @Singleton
    fun provideQuoteServiceDao(retrofit: Retrofit): QuoteService =
        retrofit.create(QuoteService::class.java)

}