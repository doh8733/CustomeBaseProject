package com.example.customebaseproject.bus

import android.content.Context
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import org.greenrobot.eventbus.EventBus

object LiveListen {
    val publisher = MutableLiveData<Any?>()
    //create any bus listen here
}