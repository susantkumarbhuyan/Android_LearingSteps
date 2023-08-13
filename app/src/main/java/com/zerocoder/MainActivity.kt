package com.zerocoder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel
    lateinit var txtCounter: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        txtCounter = findViewById(R.id.num)
        setText()
    }

    private fun setText() {
        txtCounter.text = mainViewModel.count.toString()
    }

    fun increament(v: View) {
        mainViewModel.increament()
        setText()
    }


}