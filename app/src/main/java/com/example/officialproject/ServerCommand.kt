package com.example.schoolscientistsexample

import android.util.Log
import com.example.officialproject.Food
import com.example.officialproject.FoodList
import com.google.gson.Gson
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.json.GsonSerializer
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.http.URLBuilder
import io.ktor.http.Url
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

    public suspend fun makeOrder(list: List<Food>){
        val httpClient = HttpClient(Android) {
            install(JsonFeature) {
                serializer = GsonSerializer()
            }
        }
        val request = HttpRequestBuilder()
        request.body = list
        httpClient.post(urlString = "ms1.newtonbox.ru/phone/makeorder"){
            this.body = list
        }
    }

    // пример для Андрея Губанова
    fun createOrder(item: Int): String{
        return runBlocking { createOrderBody(item) }
    }
    suspend fun getFoodList():FoodList{
        val httpClient = HttpClient(Android) {
            install(JsonFeature) {
                serializer = GsonSerializer()
            }
        }
        return httpClient.get<FoodList>("https://ms1.newtonbox.ru/phone/list/")
    }
}
