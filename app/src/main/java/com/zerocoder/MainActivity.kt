package com.zerocoder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.zerocoder.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainVIewModel

    lateinit var binding: ActivityMainBinding

    private val btn: Button
        get() = findViewById(R.id.btn)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        mainViewModel = ViewModelProvider(this).get(MainVIewModel::class.java)
        val obj:Quete = Quete("Susant")
        binding.qt = obj
        mainViewModel.currentLiveData.observe(this) {
            binding.num.text = it
        }

        btn.setOnClickListener() {
            mainViewModel.updateLiveData()
        }

    }


}