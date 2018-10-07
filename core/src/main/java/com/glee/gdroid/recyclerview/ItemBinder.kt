package com.glee.gdroid.recyclerview

import android.support.annotation.IdRes
import android.support.annotation.LayoutRes

data class ItemBinder(
        @IdRes
        val brId: Int,
        @LayoutRes
        val layoutRes: Int) {

    companion object {
        fun of(@IdRes brId: Int, @LayoutRes layoutRes: Int) = ItemBinder(brId, layoutRes)
    }
}