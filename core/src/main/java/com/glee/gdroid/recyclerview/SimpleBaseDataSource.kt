package com.glee.gdroid.recyclerview

import android.arch.paging.ItemKeyedDataSource
import android.arch.paging.PageKeyedDataSource

/**
 *  @author liji
 *  @date  9/29/2018 4:25 PM
 *  description
 */


abstract class SimpleBaseDataSource<V : BaseItemData> : PageKeyedDataSource<Int, V>() {
    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, V>) {}


    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, V>) = loadData(params, callback)


    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, V>) {
        loadBefore(LoadParams(0, params.requestedLoadSize), object : PageKeyedDataSource.LoadCallback<Int, V>() {
            override fun onResult(data: MutableList<V>, adjacentPageKey: Int?) {
                callback.onResult(data, -1, 1)
            }
        })
    }

    abstract fun loadData(params: LoadParams<Int>, callback: LoadCallback<Int, V>)

}