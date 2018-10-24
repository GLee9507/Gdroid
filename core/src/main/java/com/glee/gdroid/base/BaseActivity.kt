package com.glee.gdroid.base

import android.annotation.SuppressLint
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.KeyEvent
import java.lang.reflect.ParameterizedType

@SuppressLint("Registered")
/**
 *  @author liji
 *  @date  9/29/2018 11:17 AM
 *  description Activity基类
 */


abstract class BaseActivity<VM : ViewModel, B : ViewDataBinding> : AppCompatActivity() {
    abstract val layoutId: Int
    lateinit var binding: B
    val vm by lazy {
        ViewModelProviders.of(this)[
                (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<VM>

        ]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil
                .setContentView(this, layoutId)
        binding.setLifecycleOwner(this)
    }
}