package com.example.customebaseproject.base.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ProgressBar
import android.widget.RelativeLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.customebaseproject.R
import com.example.customebaseproject.base.adapter.BaseEndlessAdapter
import com.example.customebaseproject.extensions.gone
import com.example.customebaseproject.extensions.visible
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class BaseEndlessRecyclerview : RelativeLayout {
    private val swipeRefresh: SwipeRefreshLayout by lazy { findViewById<SwipeRefreshLayout>(R.id.swipeRefresh) }
    private val rcvEndlessLoading: RecyclerView by lazy { findViewById<RecyclerView>(R.id.rcv_endless_loading) }

    private var mAdapter: BaseEndlessAdapter? = null

    init {
        //luon luon attachroot phai la true
        LayoutInflater.from(context).inflate(R.layout.endless_recyclerview, this, true)
        //set params for view
    }

    constructor(context: Context) : super(context) {
    }

    constructor(context: Context, attrs: AttributeSet) : super(
        context,
        attrs
    ) {
        setUp(attrs)
    }

    constructor(
        context: Context,
        attrs: AttributeSet,
        defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr) {
        setUp(attrs)
    }

    private fun setUp(attrs: AttributeSet) {
        val a =
            context!!.theme.obtainStyledAttributes(attrs, R.styleable.BaseEndlessRecyclerview, 0, 0)
        val isRefresh =
            a.getBoolean(R.styleable.BaseEndlessRecyclerview_endless_rcv_enable_refresh, true)
        swipeRefresh.isEnabled = isRefresh
    }

    fun setEnableRefresh(isRefresh: Boolean) {
        swipeRefresh.isRefreshing = isRefresh
    }

    fun setOrientationLayout(type: Int) {
        var layoutManager: RecyclerView.LayoutManager =
            LinearLayoutManager(context, type, false)
        rcvEndlessLoading.layoutManager = layoutManager
    }

    fun setAdapter(adapter: BaseEndlessAdapter) {
        mAdapter = adapter
        rcvEndlessLoading.adapter = adapter
    }

    fun setEnableLoadMore(isLoadMore: Boolean? = true): Boolean {
        return isLoadMore!!
    }

    fun setOnLoadingMore(onLoadMore: () -> Unit) {
        mAdapter?.setOnLoadMoreListener {
                mAdapter?.showLoadMore()
                onLoadMore()
        }
    }


    fun setOnRefresh(onRefresh: () -> Unit) {
        swipeRefresh.setOnRefreshListener {
            onRefresh()
        }
    }

    fun setReverseLayout(layoutManager: LayoutManager, reverse: Boolean) {
        when (layoutManager) {
            is LinearLayoutManager -> {
                layoutManager.reverseLayout = true
                layoutManager.stackFromEnd = true
            }
            is GridLayoutManager -> {
                layoutManager.reverseLayout = true
                layoutManager.stackFromEnd = true
            }
        }
    }
}