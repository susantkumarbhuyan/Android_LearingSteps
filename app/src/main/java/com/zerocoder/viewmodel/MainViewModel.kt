package com.zerocoder.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zerocoder.model.QuoteList
import com.zerocoder.repository.BaseResponse
import com.zerocoder.repository.QuoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class MainViewModel @Inject constructor(private val repository: QuoteRepository):ViewModel() {
    init {
        viewModelScope.launch (Dispatchers.IO){
            repository.getQuotes(1)
        }
    }
    val quotes :LiveData<BaseResponse<QuoteList>>
        get() = repository.quotes
}