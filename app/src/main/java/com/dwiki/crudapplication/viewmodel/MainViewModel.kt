package com.dwiki.crudapplication.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.dwiki.crudapplication.database.Quote
import com.dwiki.crudapplication.repository.QuoteRepository

class MainViewModel(application: Application): AndroidViewModel(application) {
    private val mQuoteRepository: QuoteRepository = QuoteRepository(application)

    fun getAllQuote(): LiveData<List<Quote>> = mQuoteRepository.getAllQuote()

    fun upsert(quote: Quote){
        mQuoteRepository.upsert(quote)
    }

    fun delete(quote: Quote){
        mQuoteRepository.delete(quote)
    }
}