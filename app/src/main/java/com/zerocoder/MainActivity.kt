package com.zerocoder

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast

import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider


import com.zerocoder.databinding.ActivityMainBinding
import com.zerocoder.repository.BaseResponse

import com.zerocoder.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var mainViewModel: MainViewModel
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        mainViewModel = ViewModelProvider(
            this
        )[MainViewModel::class.java]

        mainViewModel.quotes.observe(this) {
            when (it) {
                is BaseResponse.Success -> {
                    it.data?.let {
                        Toast.makeText(
                            this@MainActivity,
                            it.results.size.toString(),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
                is BaseResponse.Error -> {
                    Toast.makeText(this@MainActivity, "Some Error Occured", Toast.LENGTH_SHORT)
                        .show()
                }
                is BaseResponse.Loading -> {Toast.makeText(this@MainActivity, "Loading", Toast.LENGTH_SHORT)
                    .show()}
                else -> {Toast.makeText(this@MainActivity, "Server Error", Toast.LENGTH_SHORT)
                    .show()}
            }
        }
    }
}