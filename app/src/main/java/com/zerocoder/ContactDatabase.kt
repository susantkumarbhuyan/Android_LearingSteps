package com.zerocoder

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Contact::class], version = 1)
@TypeConverters(Convertors::class)
abstract class ContactDatabase : RoomDatabase() {
    abstract fun contactDao(): ContactDao

    companion object {
        @Volatile
        private var DB_INSTANCE: ContactDatabase? = null

        fun getDB(context: Context): ContactDatabase? {
            if (DB_INSTANCE == null) {
                synchronized(this) {}
                DB_INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    ContactDatabase::class.java,
                    "contactDB"
                )
                    .build()
            }
            return DB_INSTANCE!!
        }

    }
}