package com.glee.sample

import android.arch.lifecycle.ViewModel
import android.arch.paging.LivePagedListBuilder
import com.glee.gdroid.recyclerview.ItemBinder
import com.glee.gdroid.recyclerview.SimpleBaseDataSource

class MainViewModel : ViewModel() {
    val itemBinder=ItemBinder.of(BR.data, R.layout.recycler_item)

    val itemData by lazy {
        LivePagedListBuilder(SimpleBaseDataSource.factory<Test> { params, callback ->
            Thread.sleep(1000)
            val list = ArrayList<Test>()
            for (i in 0 until params.requestedLoadSize) {
                list.add(Test("test" + (params.key * 10 + i).toString(), params.key * 10 + i))
            }
            callback.onResult(
                    list, params.key + 1
            )
        }, 10).build()
    }
}