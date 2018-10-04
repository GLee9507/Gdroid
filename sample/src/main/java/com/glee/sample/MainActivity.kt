package com.glee.sample

import android.arch.lifecycle.Observer
import android.arch.paging.LivePagedListBuilder
import android.os.Bundle
import com.glee.gdroid.base.BaseActivity
import com.glee.gdroid.recyclerview.BaseItemData
import com.glee.gdroid.recyclerview.BaseRecyclerViewAdapter
import com.glee.gdroid.recyclerview.SimpleBaseDataSource
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    override val layoutId = R.layout.activity_main
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val adapter = BaseRecyclerViewAdapter<Test>(R.layout.recycler_item, BR.text)
        recycler.adapter = adapter
        val liveData = LivePagedListBuilder(SimpleBaseDataSource.factory<Test> { params, callback ->
            Thread.sleep(1000)
            val list = ArrayList<Test>()
            for (i in 0 until params.requestedLoadSize) {
                list.add(Test("test"+(params.key * 10 + i).toString(), params.key * 10 + i))
            }
            callback.onResult(
                    list, params.key + 1
            )
        }, 10
        ).build()
        liveData.observe(this, Observer {
            adapter.submitList(it)
        })

    }

}

data class Test(val string: String, override val itemId: Int) : BaseItemData