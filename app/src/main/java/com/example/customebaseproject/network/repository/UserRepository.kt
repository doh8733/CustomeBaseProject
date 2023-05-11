package com.example.customebaseproject.network.repository

import com.example.customebaseproject.network.RetrofitClient.apiConfig

class UserRepository  {
        suspend fun getAllUser() = apiConfig.getUser()
}