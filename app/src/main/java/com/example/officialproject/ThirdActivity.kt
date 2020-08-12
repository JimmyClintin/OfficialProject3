package com.example.officialproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.example.schoolscientistsexample.ServerCommand
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import kotlinx.android.synthetic.main.activity_third.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ThirdActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)
        ServerCommand()
        ShowPinCode1()
    }
    private fun ShowPinCode1() {
        val tAndrey = ServerCommand()
        val str = tAndrey.createOrder(4)

        textView.text = str

        val myToast = Toast.makeText(this, str, Toast.LENGTH_SHORT)
        myToast.show()
    }
}
