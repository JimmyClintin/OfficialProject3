package com.example.officialproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.util.Log
import androidx.core.content.ContextCompat.startActivity
import com.example.schoolscientistsexample.ServerCommand
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.NumberFormatException

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // perehod mezdu ekranami
    }

    fun randomMe(view: View) {  // метод для перехода через активити

        // Create an Intent to start the second activity
        val randomIntent = Intent(this, SecondActivity::class.java)

        // Start the new activity.
        startActivity(randomIntent)
    }


}



