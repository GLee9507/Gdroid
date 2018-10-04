package com.glee.gdroid.recyclerview

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleOwner
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
import android.widget.LinearLayout

class SimpleListAdapter<V : BaseItemData>(
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

    private val headers = ArrayList<View>(2)
    private val footers = ArrayList<View>(2)

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        inflater = LayoutInflater.from(recyclerView.context)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): BaseViewHolder {
        return BaseViewHolder(DataBindingUtil.inflate<ViewDataBinding>(inflater, itemLayoutId, viewGroup, false))
    }

    override fun onBindViewHolder(p0: BaseViewHolder, p1: Int) = with(p0.binding) {
        setVariable(brId, getItem(p1))
        executePendingBindings()
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + headers.size + footers.size
    }

    override fun getItemViewType(position: Int): Int {
        val itemCount = super.getItemCount()
        if (position < headers.size || position > itemCount + headers.size) {
            return position
        }
        return ITEM_VIEW
    }

    fun addHeader(headerView: LifecycleOwner) {

    }

    override fun onViewRecycled(holder: BaseViewHolder) {
        super.onViewRecycled(holder)
    }

    companion object {
        const val ITEM_VIEW = -1
    }
}

