package com.example.officialproject

data class FoodList(val paginator: String , val page_obj: String, val is_paginated: Boolean, val nameoffood_list: List<Food>)