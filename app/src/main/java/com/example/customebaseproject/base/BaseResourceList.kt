package com.example.customebaseproject.base

data class BaseResourceList<out T>(val status :BaseType, val data :List<T>? = null, val message :String? = null) {
    companion object{
        fun<T> success(data: List<T>?):BaseResourceList<T> = BaseResourceList(status = BaseType.SUCCESS, data = data, message = null)
        fun<T> loading():BaseResourceList<T> = BaseResourceList(status = BaseType.LOADING,data = null, message = null)
        fun<T> error(message: String?):BaseResourceList<T> = BaseResourceList(status = BaseType.ERROR,data = null,message)

    }
}