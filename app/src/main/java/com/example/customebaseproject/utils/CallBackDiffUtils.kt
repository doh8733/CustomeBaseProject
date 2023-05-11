package com.example.customebaseproject.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.customebaseproject.base.adapter.BaseEndlessAdapter

class CallBackDiffUtils() : DiffUtil.ItemCallback<BaseEndlessAdapter.Models>() {
    override fun areItemsTheSame(
        oldItem: BaseEndlessAdapter.Models,
        newItem: BaseEndlessAdapter.Models
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: BaseEndlessAdapter.Models,
        newItem: BaseEndlessAdapter.Models
    ): Boolean {
        return  oldItem == newItem
    }


}