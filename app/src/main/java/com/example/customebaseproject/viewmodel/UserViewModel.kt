package com.example.customebaseproject.viewmodel

import android.util.Log
import androidx.lifecycle.liveData
import com.example.customebaseproject.instance.Instance
import com.example.customebaseproject.base.BaseResourceList
import com.example.customebaseproject.base.BaseViewModel
import com.example.customebaseproject.network.repository.UserRepository
import kotlinx.coroutines.Dispatchers

class UserViewModel():BaseViewModel() {
    private val userRepository: UserRepository = Instance(UserRepository::class.java).newInstanceRepository(UserRepository::class.java).newInstance()

    fun getAllUser() = liveData(Dispatchers.IO){
        emit(BaseResourceList.loading())
        try {
            emit(BaseResourceList.success(userRepository.getAllUser().body()))
        }catch (e :Exception){
            emit(BaseResourceList.error(e.message))
        }
    }
}