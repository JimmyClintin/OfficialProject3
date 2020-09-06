package com.example.schoolscientistsexample

import android.util.Log
import com.example.officialproject.*
import com.google.gson.Gson

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServerCommand {
    val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    val okClient =
        OkHttpClient.Builder().addInterceptor(interceptor).retryOnConnectionFailure(true).build()
    val retrofit = Retrofit.Builder().baseUrl("https://ms1.newtonbox.ru/")
        .addConverterFactory(GsonConverterFactory.create()).client(okClient).build()

// {"name":"Andrey", "last_name":"Gubanov"} class name = "andrey" last_name= (это просто пример)


    // пример для Андрея Губанова
    /* fun createOrder(item: Int): String{
         return runBlocking { createOrderBody(item) }
     } */
    suspend fun getFoodList(): FoodList { // Выгрузка меню
        val foodService = retrofit.create(FoodService::class.java)
        return foodService.getFoodList()
    }

    suspend fun makeOrder(foods: Order): String { //отправка заказа
        val foodService = retrofit.create(FoodService::class.java)
        val hashFoods = HashMap<String, String>()
        hashFoods.put("order", Gson().toJson(foods.order))
        val a = foodService.makeOrder(hashFoods)
        return a

    }
}