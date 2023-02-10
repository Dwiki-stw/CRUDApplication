package com.dwiki.crudapplication.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database(
    entities = [Quote::class],
    version = 1
)
abstract class QuoteRoomDatabase : RoomDatabase() {

    abstract fun quoteDao(): QuoteDao

    companion object{
        @Volatile
        private var INSTANCE: QuoteRoomDatabase? = null

        @OptIn(InternalCoroutinesApi::class)
        @JvmStatic
        fun getDatabase(context: Context): QuoteRoomDatabase {
            if (INSTANCE == null){
                synchronized(this){
                    INSTANCE = buildDatabase(context)
                }
            }
            return INSTANCE as QuoteRoomDatabase
        }

        private fun buildDatabase(context: Context): QuoteRoomDatabase{
            return Room.databaseBuilder(
                context.applicationContext,
                QuoteRoomDatabase::class.java,
                "quote_database"
            ).build()
        }
    }
}