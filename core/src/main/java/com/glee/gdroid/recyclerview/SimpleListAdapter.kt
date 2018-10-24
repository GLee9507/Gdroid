package com.glee.gdroid.recyclerview

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.Transformations
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import android.arch.paging.PagedListAdapter
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

class SimpleListAdapter<V : BaseItemData>(
        private val itemBinder: ItemBinder<V>
) : PagedListAdapter<V, BaseViewHolder>(
        object : DiffUtil.ItemCallback<V>() {
            override fun areItemsTheSame(p0: V, p1: V) = p0 == p1
            override fun areContentsTheSame(p0: V, p1: V) = p0.itemId == p1.itemId
        }
) {

    private lateinit var inflater: LayoutInflater


    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        inflater = LayoutInflater.from(recyclerView.context)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): BaseViewHolder =
            BaseViewHolder(DataBindingUtil.inflate<ViewDataBinding>(inflater, itemBinder.layoutRes, viewGroup, false))


    override fun onBindViewHolder(p0: BaseViewHolder, p1: Int) = with(p0.binding) {
        setVariable(itemBinder.brId, getItem(p1))
        executePendingBindings()
    }


    fun uncheckSubmitList(pagedList: PagedList<*>?) {
        super.submitList(pagedList as PagedList<V>?)
    }

}


