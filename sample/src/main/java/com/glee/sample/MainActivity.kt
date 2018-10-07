package com.glee.sample

import android.os.Bundle
import com.glee.gdroid.base.BaseActivity
import com.glee.gdroid.recyclerview.BaseItemData
import com.glee.sample.databinding.ActivityMainBinding

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {
    override val layoutId = R.layout.activity_main
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = vm
    }
}

data class Test(val string: String, override val itemId: Int) : BaseItemData