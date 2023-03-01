package com.dwiki.crudapplication.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.dwiki.crudapplication.database.Quote
import com.dwiki.crudapplication.database.QuoteDao
import com.dwiki.crudapplication.database.QuoteRoomDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class QuoteRepository(application: Application) {
    private val mQuoteDao: QuoteDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = QuoteRoomDatabase.getDatabase(application)
        mQuoteDao = db.quoteDao()
    }

    fun getAllQuote(): LiveData<List<Quote>> = mQuoteDao.getAllQuotes()

    fun upsert (quote: Quote){
        executorService.execute{mQuoteDao.upsert(quote)}
    }

    fun delete(quote: Quote){
        executorService.execute { mQuoteDao.delete(quote) }
    }
}