package com.zerocoder.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zerocoder.model.QuoteList
import com.zerocoder.repository.QuoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: QuoteRepository):ViewModel() {
    init {
        viewModelScope.launch (Dispatchers.IO){
            repository.getQuotes(1)
        }
    }

    val quotes :LiveData<QuoteList>
        get() = repository.quotes
}