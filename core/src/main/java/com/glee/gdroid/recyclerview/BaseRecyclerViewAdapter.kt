package com.glee.gdroid.recyclerview

import android.arch.paging.PagedListAdapter
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.annotation.IdRes
import android.support.annotation.LayoutRes
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

/**
 *  @author liji
 *  @date  9/29/2018 11:36 AM
 *  description
 */


class BaseRecyclerViewAdapter(
        @LayoutRes
        private val itemLayoutId: Int,
        @IdRes
        private val brId: Int
) : PagedListAdapter<BaseItemData, BaseViewHolder>(DIFF) {

    lateinit var inflater: LayoutInflater
    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        inflater = LayoutInflater.from(recyclerView.context)
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): BaseViewHolder =
            BaseViewHolder(DataBindingUtil.inflate<ViewDataBinding>(inflater, itemLayoutId, p0, false))

    override fun onBindViewHolder(p0: BaseViewHolder, p1: Int) {
        val binding = p0.binding
        binding.setVariable(brId, getItem(p1))
        binding.executePendingBindings()
    }

    companion object {
        private val DIFF = object : DiffUtil.ItemCallback<BaseItemData>() {
            override fun areItemsTheSame(p0: BaseItemData, p1: BaseItemData) = p0 == p1
            override fun areContentsTheSame(p0: BaseItemData, p1: BaseItemData) = p0.itemId == p1.itemId
        }
    }
}