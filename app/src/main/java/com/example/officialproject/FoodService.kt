package com.example.officialproject

import retrofit2.Call
import retrofit2.http.*

interface FoodService { // Cоздаются аннотации
    @GET("phone/list/")  // Cоздается GET-запрос
    suspend fun getFoodList(): FoodList
    @POST("phone/makeorder/")  // Cоздается Post-запрос
    @FormUrlEncoded
    suspend fun makeOrder(@FieldMap foods: HashMap<String, String>):String
}
