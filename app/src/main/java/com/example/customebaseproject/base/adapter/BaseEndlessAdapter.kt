package com.example.customebaseproject.base.adapter

import android.content.Context
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.customebaseproject.R
import com.example.customebaseproject.extensions.inflate
import com.example.customebaseproject.utils.CallBackDiffUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicInteger

abstract class BaseEndlessAdapter(var context: Context, loadingMore: Boolean) :
    ListAdapter<BaseEndlessAdapter.Models, RecyclerView.ViewHolder>(
        AsyncDifferConfig.Builder<Models>(CallBackDiffUtils())
            .setBackgroundThreadExecutor(Executors.newSingleThreadExecutor()).build()
    ) {

    data class Models(
        var model: Any?,
        var viewType: Int,
        var id: Int = AtomicInteger().getAndIncrement()
    )

    var listModelWrapper = mutableListOf<Models>()
    private var recyclerView: RecyclerView? = null
    var isLoadMore = false
    private var isDisableLoadMore = false
    private var onLoadMoreEvent: () -> Unit = {}

    companion object {
        const val LOAD_MORE = 1
        const val NORMAL = 0
    }

    var dataModelsWrapper: MutableLiveData<List<Models>> = MutableLiveData()

    fun <T> getItem(model: Class<T>, position: Int): T? =
        model.cast(listModelWrapper[position].model)

    fun addModels(listModels: List<Any>) {
        addModel(listModels, 0, listModels.size - 1)
    }

    fun addModel(listModels: List<Any>, form: Int, to: Int) {
        for (i in form..to) {
            addModel(listModels[i], NORMAL)

        }
    }

    fun addModel(model: Any?) {
        addModel(model, NORMAL)
    }

    fun addModel(model: Any?, viewType: Int) {
        listModelWrapper.add(Models(model, viewType))
        dataModelsWrapper.value = listModelWrapper
    }

    fun clear() {
        listModelWrapper.clear()
        dataModelsWrapper.value = listModelWrapper
    }

    fun showLoadMore() {
        addModel(model = null, LOAD_MORE)
    }

    fun hideLoadMore() {
        if (isLoadMore) {
            var position = listModelWrapper.size - 1
            listModelWrapper.removeAt(position)
            Log.e("TAG", "hideLoadMore: $listModelWrapper")
            dataModelsWrapper.value = listModelWrapper
        }
    }

    override fun getItemViewType(position: Int): Int {
        return listModelWrapper[position].viewType
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return solvedOnCreateViewHolder(parent, viewType)!!
    }

    override fun getItemCount(): Int {
        return listModelWrapper.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        solvedOnBindViewHolder(holder, position)
    }

    open fun solvedOnCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder? {
        return when (viewType) {
            LOAD_MORE -> {
                LoadingViewHolder(parent.inflate(R.layout.include_item_loadmore))
            }
            else -> {
                initOnCreateViewHolder(parent)
            }
        }
    }

    abstract fun initOnCreateViewHolder(parent: ViewGroup): ViewHolder?

    open fun solvedOnBindViewHolder(holder: ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            LOAD_MORE -> {

            }
            else -> {
                initOnBindViewHolder(holder as OnBindViewHolder, position)
            }
        }

    }

    abstract fun initOnBindViewHolder(holder: OnBindViewHolder, position: Int)

    abstract class OnBindViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    class LoadingViewHolder(itemView: View) : ViewHolder(itemView)

    override fun submitList(list: MutableList<Models>?) {
        super.submitList(ArrayList<Models>(list ?: listOf()))
    }

    //create on loading more event
    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                when (newState) {
                    RecyclerView.SCROLL_STATE_IDLE -> {
                        var firstItemPosition = 0
                        var lastItemPosition = 0

                        if (isDisableLoadMore || isLoadMore) {
                            return
                        }
                        when (val layoutManager = recyclerView.layoutManager) {
                            is LinearLayoutManager -> {
                                firstItemPosition = layoutManager.findFirstVisibleItemPosition()
                                lastItemPosition =
                                    layoutManager.findLastCompletelyVisibleItemPosition()
                            }
                            is GridLayoutManager -> {
                                firstItemPosition = layoutManager.findFirstVisibleItemPosition()
                                lastItemPosition =
                                    layoutManager.findLastCompletelyVisibleItemPosition()
                            }
                        }
                        if (firstItemPosition > 0 && lastItemPosition == itemCount - 1) {
                            isLoadMore = true
                            onLoadMoreEvent()
                        }

                    }
                    else -> {

                    }
                }
            }

        })
    }

    fun enableLoadingMore(enable: Boolean) {
        this.isDisableLoadMore = !enable
    }

    fun setOnLoadMoreListener(onLoadMore: () -> Unit) {
        onLoadMoreEvent = onLoadMore
        enableLoadingMore(onLoadMoreEvent != null)

    }
}