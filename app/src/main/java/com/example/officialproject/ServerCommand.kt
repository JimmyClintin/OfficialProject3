package com.example.schoolscientistsexample

import android.util.Log
import com.example.officialproject.FoodList
import com.google.gson.Gson
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.json.GsonSerializer
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.request.get
import kotlinx.coroutines.runBlocking

class ServerCommand{

    private val client = HttpClient()

    private suspend fun createOrderBody(item: Int): String{
        try{
            var query = "https://ms1.newtonbox.ru/terminal0/" + item
            val res = client.get<String>(query)
            Log.i(query + " Simple case ", res)
            return res
        }
        catch (th : Throwable) {
            return "ошибка"
        }
    }


    // пример для Андрея Губанова
    fun createOrder(item: Int): String{
        return runBlocking { createOrderBody(item) }
    }
    suspend fun getFoodList(){
        val httpClient = HttpClient(Android) {
            install(JsonFeature) {
                serializer = GsonSerializer()
            }
        }
        val list = httpClient.get<FoodList>("https://ms1.networkbox.ru/phone/list/")
    }
}
