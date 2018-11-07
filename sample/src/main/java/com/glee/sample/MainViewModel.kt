package com.glee.sample

import android.arch.lifecycle.ViewModel
import android.arch.paging.PagedList
import com.glee.gdroid.recyclerview.ItemBinder

class MainViewModel : ViewModel() {
    val itemBinder = ItemBinder<Test>(
            BR.data,
            R.layout.recycler_item, PagedList.Config.Builder().setPageSize(10).build())
    { params, callback ->
        val array = arrayOfNulls<Test>(params.requestedLoadSize)
        for (i in 0 until params.requestedLoadSize) {
            val n = params.key * 10 + i
            array[i] = Test(n.toString(), n)
        }
        callback.onResult(array.toList(), params.key + 1)
    }
}