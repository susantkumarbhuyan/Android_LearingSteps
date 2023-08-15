package com.zerocoder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.zerocoder.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.Date

class MainActivity : AppCompatActivity() {
    lateinit var database: ContactDatabase
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        database = ContactDatabase.getDB(this)!!

        GlobalScope.launch {
            database.contactDao().insertContact(Contact(0, "Susant", "9090", Date()))
        }
        binding.btn.setOnClickListener {
            database.contactDao().getContact().observe(this) {
                Log.d("MYCODE", it.toString())
            }
        }

    }


}