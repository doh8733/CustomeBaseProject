package com.example.customebaseproject.base

import androidx.lifecycle.ViewModel

open class BaseViewModel: ViewModel() {
    override fun onCleared() {
        super.onCleared()
        //do something
    }
}