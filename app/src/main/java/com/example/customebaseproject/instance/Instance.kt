package com.example.customebaseproject.instance

class Instance <T>(var classRepo: Class<T>?){
    fun newInstanceRepository(data :Class<T>?):Class<T>{
        if (classRepo == null){
            classRepo = data
        }
        return classRepo!!
    }
}