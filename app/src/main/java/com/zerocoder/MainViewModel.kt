package com.zerocoder

import android.view.View
import androidx.lifecycle.ViewModel

class MainViewModel( val counter:Int): ViewModel() {

    var count: Int = counter
    fun increament() {
        count++
    }
}