package com.dwiki.crudapplication.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface QuoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(quote: Quote)

    @Delete
    fun delete(quote: Quote)

    @Query("SELECT * from quote ORDER BY id")
    fun getAllQuotes(): LiveData<List<Quote>>
}