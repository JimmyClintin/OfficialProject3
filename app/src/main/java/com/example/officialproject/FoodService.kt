package com.example.officialproject

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface FoodService { // Cоздаются аннотации
    @GET("phone/list/")  // Cоздается GET-запрос
    suspend fun getFoodList(): FoodList
    @POST("phone/makeorder/")  // Cоздается Post-запрос
    suspend fun makeOrder(@Body foods: Set<OrderFood>)
}
