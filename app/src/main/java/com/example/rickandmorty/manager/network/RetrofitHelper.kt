package com.example.rickandmorty.manager.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitHelper {

    companion object {

        private val baseURL: String = "https://rickandmortyapi.com/api/"
        private val timeOut: Long = 30

        fun getRetrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(getHttpClient())
                .build()
        }
        private fun getHttpClient(): OkHttpClient {
            val interceptor = LoggingInterceptor()
            return OkHttpClient.Builder()
                .readTimeout(timeOut, TimeUnit.SECONDS)
                .connectTimeout(timeOut, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .addInterceptor(interceptor.create())
                .build()
        }
    }
}