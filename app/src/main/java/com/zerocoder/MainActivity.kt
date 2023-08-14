package com.zerocoder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainVIewModel
     private val txtCounter: TextView
         get() = findViewById(R.id.num)

    private val btn: Button
        get() =  findViewById(R.id.btn)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainViewModel = ViewModelProvider(this,).get(MainVIewModel::class.java)
        mainViewModel.currentLiveData.observe(this ){
            txtCounter.text = it
        }

        btn.setOnClickListener(){
            mainViewModel.updateLiveData()
        }

    }




}