package com.zerocoder

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Contact::class], version = 2)
@TypeConverters(Convertors::class)
abstract class ContactDatabase : RoomDatabase() {
    abstract fun contactDao(): ContactDao

    companion object {

        private val migration_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE contact ADD COLUMN isActive INTEGER NOT NULL DEFAULT(1)")
            }
        }

        @Volatile
        private var DB_INSTANCE: ContactDatabase? = null

        fun getDB(context: Context): ContactDatabase? {
            if (DB_INSTANCE == null) {
                synchronized(this) {}
                DB_INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    ContactDatabase::class.java,
                    "contactDB"
                ).addMigrations(migration_1_2)
                    .build()
            }
            return DB_INSTANCE!!
        }

    }
}