package com.zerocoder.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.zerocoder.model.Result

@Database(entities = [Result::class], version = 1)
abstract class QuoteDb : RoomDatabase() {
    abstract fun quoteDao(): QuoteDao

    companion object {
        @Volatile
        private var DB_INSTANCE: QuoteDb? = null
        fun getQuoteDB(context: Context): QuoteDb {
            if (DB_INSTANCE == null) {
                synchronized(this) {
                    DB_INSTANCE =
                        Room.databaseBuilder(context, QuoteDb::class.java, "quoteDB").build()
                }
            }
            return DB_INSTANCE!!
        }
    }
}