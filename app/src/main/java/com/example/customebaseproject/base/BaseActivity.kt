package com.example.customebaseproject.base

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.CalendarContract.Colors
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.viewbinding.ViewBinding
import com.example.customebaseproject.R
import com.example.customebaseproject.base.BaseType.*

abstract class BaseActivity<B : ViewBinding>() : AppCompatActivity() {
    lateinit var binding: B
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = layoutId
        setContentView(binding.root)
        with(window) {
            //  https://developer.android.com/reference/android/view/Window#setStatusBarColor(int)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
            setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
//chinh them trong file theme =))
            window.statusBarColor = Color.TRANSPARENT
            window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)

        }
        initData()
        initViews()
        initListener()
        initLiveData()
    }

    abstract val layoutId: B
    abstract fun initData()
    abstract fun initViews()
    abstract fun initListener()
    abstract fun initLiveData()

    open fun setWindowFlag(activity: Activity, bits: Int, on: Boolean) {
        val win: Window = activity.window
        val winParams: WindowManager.LayoutParams = win.attributes
        if (on) {
            winParams.flags = winParams.flags or bits
        } else {
            winParams.flags = winParams.flags and bits.inv()
        }
        win.attributes = winParams
    }
    protected fun <T> goNextActivity(activity: Class<T>, bundle: Bundle? = null) {
        val i = Intent(this, activity)
        bundle?.let { i.putExtras(it) }
        startActivity(i)
    }

    protected fun handlerObjectResponse(status: BaseResourceObject<*>) {
        when (status.status) {
            LOADING -> {
                showLoadingDiaLog()
            }
            SUCCESS -> {
                getObjectResponse(status.data)
                hideLoadingDialog()
            }
            ERROR -> {
                handlerObjectErrorResponse<Unit>(status.message)
            }
        }
    }

    protected fun handlerListResponse(status: BaseResourceList<*>) {
        when (status.status) {
            LOADING -> {
                showLoadingDiaLog()
                Toast.makeText(this, "LOADING", Toast.LENGTH_SHORT).show()
            }
            SUCCESS -> {
                getListObjectResponse(status.data)
            }
            ERROR -> {
                handlerListObjectErrorResponse<Unit>(status.message)
            }
        }
    }

    open fun <U> getObjectResponse(data: U) {

    }

    open fun <U> handlerObjectErrorResponse(message: String?) {

    }

    open fun <U> getListObjectResponse(list: List<U>?) {

    }

    open fun <U> handlerListObjectErrorResponse(message: String?) {

    }

    private fun showLoadingDiaLog() {

    }

    private fun hideLoadingDialog() {

    }


}