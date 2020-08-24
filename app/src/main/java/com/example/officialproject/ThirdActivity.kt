package com.example.officialproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.PinnedPositions.pin
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.schoolscientistsexample.ServerCommand
import kotlinx.android.synthetic.main.activity_second.*
import kotlinx.android.synthetic.main.activity_third.*
import kotlinx.android.synthetic.main.activity_third.textView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.CertificatePinner.pin
import kotlinx.android.synthetic.main.activity_third.textView as textView1

class ThirdActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)
        val showPin=intent.getStringExtra("yourPin")
        textView.text = showPin.toString()
    }
    }


