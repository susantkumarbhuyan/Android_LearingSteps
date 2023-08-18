package com.zerocoder.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.zerocoder.model.Result
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Database(entities = [Result::class], version = 1)
abstract class QuoteDb : RoomDatabase() {
    abstract fun quoteDao(): QuoteDao
}

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Volatile
    private var DB_INSTANCE: QuoteDb? = null

    @Singleton // Tell Dagger-Hilt to create a singleton accessible everywhere in ApplicationCompenent (i.e. everywhere in the application)
    @Provides
    fun getQuoteDB(@ApplicationContext context: Context): QuoteDb {
        if (DB_INSTANCE == null) {
            synchronized(this) {
                DB_INSTANCE =
                    Room.databaseBuilder(context, QuoteDb::class.java, "quoteDB").build()
            }
        }
        return DB_INSTANCE!!
    }

    @Provides
    @Singleton
    fun provideUserDao(quoteDb: QuoteDb): QuoteDao = quoteDb.quoteDao()
}