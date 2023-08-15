package com.zerocoder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider

import com.zerocoder.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    lateinit var mainViewModel: MainViewModel
    lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val dao = QuoteDatabase.getDB(applicationContext).quoteDao()

        val quoteRepository = QuoteRepository(dao)
        mainViewModel = ViewModelProvider(
            this,
            MainViewModelFactory(quoteRepository)
        )[MainViewModel::class.java]

        mainViewModel.getQuote().observe(this) {
            binding.quotes = it.toString()
        }

        binding.btnAddQuote.setOnClickListener {
            val quote = Quote(0, "This is Testing", "Testing")
            mainViewModel.insertQuote(quote)
        }

    }


}