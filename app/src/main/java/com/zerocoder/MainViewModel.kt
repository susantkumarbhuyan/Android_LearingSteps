package com.zerocoder

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel:ViewModel() {
    val currentLiveData: MutableLiveData<String> by lazy {
        MutableLiveData<String>("This is fact")
    }

    fun updateLiveData() {
        currentLiveData.value = "Another fact"
    }
}