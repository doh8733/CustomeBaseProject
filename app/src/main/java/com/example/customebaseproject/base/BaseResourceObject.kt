package com.example.customebaseproject.base

data class BaseResourceObject<out T>(val status :BaseType, val data :T? = null, val message :String?) {
    companion object{
        fun<T> success(data :T):BaseResourceObject<T> = BaseResourceObject(status = BaseType.SUCCESS, data = data, message = null)
        fun<T> loading(data: T?):BaseResourceObject<T> = BaseResourceObject(status = BaseType.LOADING,data = null, message = null)
        fun<T> error(message : String?):BaseResourceObject<T> = BaseResourceObject(status = BaseType.ERROR,data = null,message)
    }
}