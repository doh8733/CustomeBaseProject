package com.example.customebaseproject.model


import com.google.gson.annotations.SerializedName

data class UserItem(
    @SerializedName("body")
    var body: String?,
    @SerializedName("id")
    var id: Int?,
    @SerializedName("title")
    var title: String?,
    @SerializedName("userId")
    var userId: Int?
)