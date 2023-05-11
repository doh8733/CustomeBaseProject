package com.example.customebaseproject.extensions

import androidx.lifecycle.MutableLiveData
import com.example.customebaseproject.base.BaseResourceList
import com.example.customebaseproject.base.BaseResourceObject

typealias ResourceObject<T> = MutableLiveData<BaseResourceObject<T>>
typealias ResourceListObj<T> = MutableLiveData<BaseResourceList<T>>