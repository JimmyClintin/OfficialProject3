package com.example.schoolscientistsexample

import android.util.Log
import com.example.officialproject.Food
import com.example.officialproject.FoodList
import com.google.gson.Gson
import io.ktor.client.HttpClient
import io.ktor.client.call.call
import io.ktor.client.engine.android.Android
import io.ktor.client.features.json.GsonSerializer
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.request.*
import io.ktor.client.request.forms.FormDataContent
import io.ktor.client.request.forms.submitForm
import io.ktor.client.response.readText
import io.ktor.http.*
import kotlinx.coroutines.runBlocking

class ServerCommand{

    private val client = HttpClient()

    suspend fun postHeadersCase(client: HttpClient) {
        val data: String = client.post("https://ms1.newtonbox.ru/phone/makeorder/") {
        }
        Log.i("https://ms1.newtonbox.ru/phone/makeorder/ Post case", data)
    }

    private fun HttpRequestBuilder.fillHeadersCaseParameters() {
        url.parameters.appendAll(
            parametersOf(
                "order" to listOf("id", "value") // + список параметров в строку запроса
            )
        )

    }



    public suspend fun makeOrder(list: List<Food>){
        val httpClient = HttpClient(Android) {
            install(JsonFeature) {
                serializer = GsonSerializer()
            }
        }

    }

    // пример для Андрея Губанова
    /* fun createOrder(item: Int): String{
         return runBlocking { createOrderBody(item) }
     } */
    suspend fun getFoodList():FoodList{
        val httpClient = HttpClient(Android) {
            install(JsonFeature) {
                serializer = GsonSerializer()
            }
        }
        return httpClient.get<FoodList>("https://ms1.newtonbox.ru/phone/list/")
    }
}