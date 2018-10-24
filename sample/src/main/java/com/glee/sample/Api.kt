package com.glee.sample

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import retrofit2.Retrofit
import java.io.IOException

/**
 *  @author liji
 *  @date  10/8/2018 1:14 PM
 *  description
 */


interface Api {

}

val NET by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
    Retrofit.Builder()
            .baseUrl("http://www.wanandroid.com")
            .client(
                    OkHttpClient.Builder()
                            .addNetworkInterceptor {
                               response(it)
                            }
                            .build()
            )
            .build()
}

private fun response(chain: Interceptor.Chain): Response? {
    val request = chain.request()
    return try {
        chain.proceed(request)
    } catch (e: Exception) {
        null
    }
}