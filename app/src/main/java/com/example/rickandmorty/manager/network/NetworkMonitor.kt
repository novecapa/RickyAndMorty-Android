package com.example.rickandmorty.manager.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

interface NetworkMonitorInterface {
    fun isNetworkAvailable(context: Context): Boolean
}
class NetworkMonitor: NetworkMonitorInterface {

    override fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
        return capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
    }
}