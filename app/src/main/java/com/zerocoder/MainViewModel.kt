package com.zerocoder

import android.view.View
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    var count: Int = 0
    fun increament() {
        count++
    }
}