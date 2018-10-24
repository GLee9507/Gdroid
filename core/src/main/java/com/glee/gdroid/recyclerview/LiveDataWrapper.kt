package com.glee.gdroid.recyclerview

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer

/**
 *  @author liji
 *  @date  10/24/2018 3:36 PM
 *  description
 */


class LiveDataWrapper<T> : LiveData<T>() {

    var liveData: LiveData<T>? = null

    override fun setValue(value: T) {
        super.setValue(value)
    }

    fun addSource(liveData: LiveData<T>) {
        this.liveData = liveData
        liveData.observe(owner, observer)
    }

    lateinit var owner: LifecycleOwner
    lateinit var observer: Observer<T>
    override fun observe(owner: LifecycleOwner, observer: Observer<T>) {
        this.observer = observer
        this.owner = owner
    }


    override fun hasObservers(): Boolean {
        if (liveData != null) {
            return liveData!!.hasObservers()
        }
        return super.hasObservers()
    }

    override fun removeObservers(owner: LifecycleOwner) {
        if (liveData != null) {
            liveData!!.removeObservers(owner)
        }
        super.removeObservers(owner)
    }

    override fun observeForever(observer: Observer<T>) {
        if (liveData != null) {
            liveData!!.observeForever(observer)
        }
    }

    override fun removeObserver(observer: Observer<T>) {
        if (liveData != null) {
            liveData!!.removeObserver(observer)
        }
        super.removeObserver(observer)
    }

    override fun getValue(): T? {
        if (liveData != null) {
            return liveData!!.value
        }
        return super.getValue()
    }

    override fun hasActiveObservers(): Boolean {
        if (liveData != null) {
            return liveData!!.hasActiveObservers()
        }
        return super.hasActiveObservers()
    }

    override fun postValue(value: T) {

    }

    override fun onActive() {
        super.onActive()
    }
}