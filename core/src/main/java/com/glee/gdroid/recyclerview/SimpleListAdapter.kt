package com.glee.gdroid.recyclerview

import android.arch.paging.PagedList
import android.arch.paging.PagedListAdapter
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.annotation.IdRes
import android.support.annotation.LayoutRes
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class SimpleListAdapter<V : BaseItemData>(
        private val itemBinder: ItemBinder
) : PagedListAdapter<V, BaseViewHolder>(
        object : DiffUtil.ItemCallback<V>() {
            override fun areItemsTheSame(p0: V, p1: V) = p0 == p1
            override fun areContentsTheSame(p0: V, p1: V) = p0.itemId == p1.itemId
        }
) {

    private lateinit var inflater: LayoutInflater

    private val headers = ArrayList<Header>(2)
    private val footers = ArrayList<Header>(2)

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        inflater = LayoutInflater.from(recyclerView.context)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): BaseViewHolder {
        return BaseViewHolder(DataBindingUtil.inflate<ViewDataBinding>(inflater, getLayoutId(viewType), viewGroup, false))
    }

    override fun onBindViewHolder(p0: BaseViewHolder, p1: Int) = with(p0.binding) {
        setVariable(itemBinder.brId, getItem(p1))
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

    @LayoutRes
    private fun getLayoutId(viewType: Int): Int {
        return when {
            viewType == ITEM_VIEW -> itemBinder.layoutRes
            viewType < headers.size -> headers[viewType].layoutId
            else -> footers[viewType].layoutId
        }
    }


    companion object {
        const val ITEM_VIEW = -1
    }


    fun uncheckSubmitList(pagedList: PagedList<*>?) {
        super.submitList(pagedList as PagedList<V>?)
    }

    fun setHeaders(headers: List<Header>?) {
        val beforeSize = this.headers.size
        this.headers.clear()
        if (headers != null) {
            this.headers.addAll(headers)
        }
        val afterSize = this.headers.size

        when {
            beforeSize > afterSize -> notifyItemRangeRemoved(0, beforeSize - afterSize)
            beforeSize < afterSize -> notifyItemRangeInserted(0, afterSize - beforeSize)
        }
        notifyItemRangeChanged(0, afterSize)

    }

    fun setFooters(footers: List<Header>?) {
        this.footers.clear()
        if (footers != null) {
            this.footers.addAll(headers)
        }

    }
}

