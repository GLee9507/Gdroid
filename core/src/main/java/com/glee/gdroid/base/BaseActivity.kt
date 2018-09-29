package com.glee.gdroid.base

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

@SuppressLint("Registered")
/**
 *  @author liji
 *  @date  9/29/2018 11:17 AM
 *  description Activity基类
 */


class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}