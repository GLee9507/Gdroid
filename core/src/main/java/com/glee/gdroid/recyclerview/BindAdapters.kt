package com.glee.gdroid.recyclerview

import android.arch.paging.PagedList
import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView

object BindAdapters {
//    @BindingAdapter("app:headers")
//    fun headers(recyclerView: RecyclerView, headers: List<Header>) {
//        val adapter = recyclerView.adapter
//        if (adapter is SimpleListAdapter<*>) {
//            val recyclerViewAdapter = adapter as SimpleListAdapter<*>?
//            recyclerViewAdapter!!.setHeaders(headers)
//        }
//    }

    @JvmStatic
    @BindingAdapter("app:itemData", "app:itemBinder")
    fun itemData(recyclerView: RecyclerView, itemData: PagedList<out BaseItemData>?, itemBinder: ItemBinder) {
        var adapter: RecyclerView.Adapter<*>? = recyclerView.adapter
        if (adapter == null) {
            adapter = SimpleListAdapter<BaseItemData>(itemBinder)
            recyclerView.adapter = adapter
        }
        if (adapter is SimpleListAdapter<*>) {
            (adapter as SimpleListAdapter<*>?)?.uncheckSubmitList(itemData)
        }
    }
}
