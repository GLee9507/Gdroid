package com.glee.gdroid.recyclerview

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PageKeyedDataSource
import android.arch.paging.PagedList
import android.support.annotation.IdRes
import android.support.annotation.LayoutRes

data class ItemBinder<V : BaseItemData>(
        @IdRes
        val brId: Int,
        @LayoutRes
        val layoutRes: Int,
        val cfg: PagedList.Config,
        val loadData: (params: PageKeyedDataSource.LoadParams<Int>, callback: PageKeyedDataSource.LoadCallback<Int, V>) -> Unit) {
    val listData by lazy {
        LivePagedListBuilder(
                SimpleBaseDataSource.factory(loadData),
                cfg
        ).build()
    }
}

