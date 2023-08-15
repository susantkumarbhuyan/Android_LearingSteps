package com.zerocoder

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Quote::class], version = 1)
abstract class QuoteDatabase : RoomDatabase() {
    abstract fun quoteDao(): QuoteDao

    companion object {
        @Volatile
        private var DB_INSTANCE: QuoteDatabase? = null
        fun getDB(context: Context): QuoteDatabase {
            if (DB_INSTANCE == null) {
                synchronized(this) {
                    DB_INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        QuoteDatabase::class.java,
                        "quoteDB"
                    ).createFromAsset("quotes.db").build()
                }
            }
            return DB_INSTANCE!!
        }
    }

}