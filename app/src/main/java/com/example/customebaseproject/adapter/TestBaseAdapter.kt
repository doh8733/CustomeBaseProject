package com.example.customebaseproject.adapter

import android.content.Context
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.customebaseproject.R
import com.example.customebaseproject.Test
import com.example.customebaseproject.base.adapter.BaseEndlessAdapter
import com.example.customebaseproject.extensions.inflate

class TestBaseAdapter(context: Context) : BaseEndlessAdapter(context,false){
    class TestBaseViewHolder(view :View) : OnBindViewHolder(view){
         val textView: TextView by lazy { view.findViewById<TextView>(R.id.textView) }
    }
    override fun initOnCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder? {
    return TestBaseViewHolder(parent.inflate(R.layout.layout_test))
    }


    override fun initOnBindViewHolder(holder: OnBindViewHolder, position: Int) {
        val item = getItem(Test::class.java,position)
        item?.let {
            if (it is Test){
                (holder as TestBaseViewHolder).textView.text = it.count.toString()
                Log.e("itititi", "initOnBindViewHolder: $it", )
            }
        }
    }
}