package com.zerocoder

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast

import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.zerocoder.api.QuoteService
import com.zerocoder.api.RetrofirHelper

import com.zerocoder.databinding.ActivityMainBinding
import com.zerocoder.repository.QuoteRepository
import com.zerocoder.viewmodel.MainVIewModelFactory
import com.zerocoder.viewmodel.MainViewModel


class MainActivity : AppCompatActivity() {
    lateinit var mainViewModel: MainViewModel
    lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val repository = (application as QuoteApplication).quoteRepository
        mainViewModel = ViewModelProvider(
            this,
            MainVIewModelFactory(repository)
        )[MainViewModel::class.java]

        mainViewModel.quotes.observe(this) {
            Toast.makeText(this@MainActivity, it.results.size.toString(), Toast.LENGTH_SHORT).show()
            Log.d("MYDATA", it.results.toString())
        }


    }


}