package com.example.customebaseproject


import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.customebaseproject.adapter.TestBaseAdapter
import com.example.customebaseproject.base.BaseActivity
import com.example.customebaseproject.base.BaseResourceObject
import com.example.customebaseproject.base.adapter.BaseEndlessAdapter
import com.example.customebaseproject.databinding.ActivityMainBinding
import com.example.customebaseproject.viewmodel.UserViewModel
import kotlinx.coroutines.Dispatchers

class MainActivity : BaseActivity<ActivityMainBinding>() {
    private val viewModel: UserViewModel by viewModels()
    override val layoutId: ActivityMainBinding
        get() = ActivityMainBinding.inflate(layoutInflater)
    val list = mutableListOf<Test>()
    private val testBaseAdapter: TestBaseAdapter by lazy { TestBaseAdapter(context = this) }
    override fun initData() {
        testBaseAdapter.dataModelsWrapper.observe(this) {
            testBaseAdapter.submitList(it as MutableList<BaseEndlessAdapter.Models>?)
        }
        getData().observe(this) {
            handlerObjectResponse(it)
        }
    }

    override fun initViews() {
        binding.rcvView.apply {
            setAdapter(testBaseAdapter)
            setOrientationLayout(LinearLayoutManager.VERTICAL)
            setOnLoadingMore {
//                setEnableLoadMore(false)
//                testBaseAdapter.addModel(Test(18))
//                Toast.makeText(this@MainActivity, "Load more succes", Toast.LENGTH_SHORT).show()\
                getData()

            }
        }
        binding.rcvView.setOnRefresh {
            testBaseAdapter.clear()
//            testBaseAdapter.addModels(list)
//            Toast.makeText(this, "Thanh cong", Toast.LENGTH_SHORT).show()
            binding.rcvView.setEnableRefresh(false)

        }
    }

    override fun initListener() {

    }

    override fun initLiveData() {
//        binding.tvText.setOnClickListener {
//            EventBus.getDefault().post(LiveEvent("ahahaha"))
//            goNextActivity(MainActivity2::class.java)
//        }
//        binding.tvText.let {
//
//        }
        list.add(Test(3))
        list.add(Test(4))
        list.add(Test(5))
        list.add(Test(6))
        list.add(Test(7))
        list.add(Test(7))
        list.add(Test(8))
        list.add(Test(9))
        list.add(Test(10))
        list.add(Test(11))
        list.add(Test(12))
        list.add(Test(13))
        list.add(Test(14))
        list.add(Test(15))
        testBaseAdapter.addModel(list,0,list.size -1)

        binding.btnCount.setOnClickListener {
            val mutableList = mutableListOf<Test>()
//            testBaseAdapter.hideLoadMore()
            //   testBaseAdapter.addModel(Test(8))

        }
    }

    override fun <U> getObjectResponse(data: U) {
        super.getObjectResponse(data)
        testBaseAdapter.hideLoadMore()
        testBaseAdapter.addModel(data)

    }

    fun getData() = androidx.lifecycle.liveData(Dispatchers.IO) {
        emit(BaseResourceObject.loading(null))
        try {
            emit(BaseResourceObject.success(Test(18)))
        } catch (e: Exception) {
            emit(BaseResourceObject.error(e.message ?: "ERROR"))
        }
    }
}


