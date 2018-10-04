package com.glee.gdroid.recyclerview

import android.arch.paging.DataSource
import android.arch.paging.ItemKeyedDataSource
import android.arch.paging.PageKeyedDataSource

/**
 *  @author liji
 *  @date  9/29/2018 4:25 PM
 *  description
 */


class SimpleBaseDataSource<V : BaseItemData>(
        private val loadData: (params: LoadParams<Int>, callback: LoadCallback<Int, V>) -> Unit
) : PageKeyedDataSource<Int, V>() {
    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, V>)=loadData(params, callback)


    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, V>) {}


    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, V>) {
        loadAfter(LoadParams(0, params.requestedLoadSize), object : PageKeyedDataSource.LoadCallback<Int, V>() {
            override fun onResult(data: MutableList<V>, adjacentPageKey: Int?) {
                callback.onResult(data, -1, 1)
            }
        })
    }



    companion object {
        fun <V : BaseItemData> factory(loadData: (params: LoadParams<Int>, callback: LoadCallback<Int, V>) -> Unit) =
                object : DataSource.Factory<Int, V>() {
                    override fun create() = SimpleBaseDataSource(loadData)
                }
    }
}


