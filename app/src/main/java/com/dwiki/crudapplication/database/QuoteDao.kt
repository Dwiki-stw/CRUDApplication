package com.dwiki.crudapplication.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface QuoteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun inser(quote: Quote)

    @Update

    @Delete
    fun delete(quote: Quote)

    @Query("SELECT * from quote")
    fun getAllQuotes(): LiveData<List<Quote>>
}