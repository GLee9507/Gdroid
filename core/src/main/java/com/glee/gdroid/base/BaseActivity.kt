package com.glee.gdroid.base

import android.annotation.SuppressLint
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.KeyEvent

@SuppressLint("Registered")
/**
 *  @author liji
 *  @date  9/29/2018 11:17 AM
 *  description Activity基类
 */


abstract class BaseActivity : AppCompatActivity() {
    abstract val layoutId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil
                .setContentView<ViewDataBinding>(this, layoutId)
                .setLifecycleOwner(this)
    }

    override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
        return super.onKeyUp(keyCode, event)
    }
}