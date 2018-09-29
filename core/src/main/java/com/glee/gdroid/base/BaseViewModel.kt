package com.glee.gdroid.base

import android.arch.lifecycle.ViewModel

/**
 *  @author liji
 *  @date  9/29/2018 11:23 AM
 *  description ViewModel 基类
 */


class BaseViewModel : ViewModel() {


    override fun onCleared() {
        super.onCleared()
    }
}