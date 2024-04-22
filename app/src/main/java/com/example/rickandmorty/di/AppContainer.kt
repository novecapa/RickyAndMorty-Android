package com.example.rickandmorty.di

import android.app.Application
import android.content.Context

class AppContainer: Application() {

    companion object {
        private var instance: AppContainer? = null
        val context: Context? get() = instance
    }

    override fun onCreate() {
        instance = this
        super.onCreate()
    }
}