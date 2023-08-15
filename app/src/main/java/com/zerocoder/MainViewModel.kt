package com.zerocoder

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val codeRepository: QuoteRepository) : ViewModel() {

    fun getQuote(): LiveData<List<Quote>> {
        return codeRepository.getQuote()
    }

    fun insertQuote(quote: Quote) {
        viewModelScope.launch(
            Dispatchers.IO
        ) {
            codeRepository.insertQuote(quote)
        }

    }
}