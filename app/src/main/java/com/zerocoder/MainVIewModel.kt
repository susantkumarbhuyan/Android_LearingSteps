package com.zerocoder

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainVIewModel : ViewModel() {
    val currentLiveData: MutableLiveData<String> by lazy {
        MutableLiveData<String>("This is fact")
    }

    fun updateLiveData() {
        currentLiveData.value = "Another fact"
    }
}