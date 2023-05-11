package com.example.customebaseproject.utils

import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import java.util.concurrent.Executors

class DiffBuildUtils<T>(val cls: DiffUtil.ItemCallback<T>) {
    var asyncDifferConfig = AsyncDifferConfig.Builder(cls)
        .setBackgroundThreadExecutor(Executors.newSingleThreadExecutor()).build()
}