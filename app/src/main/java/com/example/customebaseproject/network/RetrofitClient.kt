package com.example.customebaseproject.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    val BASE_URL = "https://jsonplaceholder.typicode.com/"
    val retrofitClient =
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .build()
    val apiConfig :AppConfig = retrofitClient.create(AppConfig::class.java)
}