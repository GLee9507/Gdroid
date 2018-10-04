package com.glee.gdroid.recyclerview

import android.arch.paging.PagedList
import android.arch.paging.PagedListAdapter
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.annotation.IdRes
import android.support.annotation.LayoutRes
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 *  @author liji
 *  @date  9/29/2018 11:36 AM
 *  description
 */


class BaseRecyclerViewAdapter<V : BaseItemData>(
        @LayoutRes
        private val itemLayoutId: Int,
        @IdRes
        private val brId: Int
) : PagedListAdapter<V, BaseViewHolder>(
        object : DiffUtil.ItemCallback<V>() {
            override fun areItemsTheSame(p0: V, p1: V) = p0 == p1
            override fun areContentsTheSame(p0: V, p1: V) = p0.itemId == p1.itemId
        }
) {

    private lateinit var inflater: LayoutInflater

    private val headers by lazy { SparseArray<View>(2) }
    private val footers by lazy { SparseArray<View>(2) }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        inflater = LayoutInflater.from(recyclerView.context)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): BaseViewHolder {
        return BaseViewHolder(DataBindingUtil.inflate<ViewDataBinding>(inflater, itemLayoutId, viewGroup, false))
    }

    override fun onBindViewHolder(p0: BaseViewHolder, p1: Int) {
        val binding = p0.binding
        binding.setVariable(brId, getItem(p1))
        binding.executePendingBindings()
    }


//    override fun getItemViewType(position: Int): Int {
//        if (position < headers.size()) {
//
//        }else{
//
//        }
//    }


}