package com.example.rickandmorty.manager.network

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor

class LoggingInterceptor: Interceptor {

    companion object {
        private val INTERCEPTOR = "INTERCEPTOR"
    }

    fun create(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor { message -> Log.i(INTERCEPTOR, message) }
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }

    override fun intercept(chain: Interceptor.Chain): Response = chain.run {
        proceed(request().newBuilder().build())
    }
}