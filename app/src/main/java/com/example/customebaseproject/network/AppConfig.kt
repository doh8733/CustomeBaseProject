package com.example.customebaseproject.network

import com.example.customebaseproject.base.BaseResourceList
import com.example.customebaseproject.model.User
import com.example.customebaseproject.model.UserItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface AppConfig {
    @Headers("Content-Type: application/json")
    @GET("posts?userId=1")
    suspend fun getUser():Response<List<UserItem>>
}