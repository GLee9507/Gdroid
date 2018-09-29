package com.glee.gdroid.datasource.net

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 *  @author liji
 *  @date  9/29/2018 1:27 PM
 *  description
 */


object Net {

    val HTTP by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(
                        OkHttpClient.Builder()
                                .build())
                .build()
                .create(service)
    }

    lateinit var baseUrl: String
    lateinit var service: Class<*>
    fun init(baseUrl: String, service: Class<*>) {
        this.baseUrl = baseUrl
        this.service = service
    }
}