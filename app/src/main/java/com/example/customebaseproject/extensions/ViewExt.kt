package com.example.customebaseproject.extensions

import android.media.Image
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.EditText
import android.widget.ImageView
import androidx.annotation.LayoutRes
import androidx.core.widget.addTextChangedListener
import com.bumptech.glide.Glide
import com.example.customebaseproject.R
import com.google.android.material.textfield.TextInputEditText

fun ImageView.loadImage(url: String) {
    Glide.with(this.context)
        .load(url)
        .placeholder(R.drawable.ic_launcher_background)
        .into(this)
}
fun ViewGroup.inflate(@LayoutRes layout :Int) :View{
    return LayoutInflater.from(this.context).inflate(layout,this,false)
}
fun View.invisible(){
  visibility = View.INVISIBLE
}
fun View.gone(){
    visibility = View.GONE
}
fun View.visible(){
    visibility = View.VISIBLE
}
