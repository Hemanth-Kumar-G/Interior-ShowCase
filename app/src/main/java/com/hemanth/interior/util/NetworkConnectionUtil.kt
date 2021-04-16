package com.hemanth.interior.util

import android.app.AlertDialog
import android.content.Context
import android.net.ConnectivityManager
import com.hemanth.interior.R

class NetworkConnectionUtil(private val context: Context) {

    fun isConnected(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = connectivityManager.activeNetworkInfo
        return netInfo != null && netInfo.isConnected
    }

     fun showInternetError(onRetry: () -> Unit,context: Context) {
        AlertDialog.Builder(context)
            .setMessage(context.getString(R.string.no_internet_error))
            .setPositiveButton(context.getString(R.string.retry)) { _, _ -> onRetry.invoke() }
            .setOnCancelListener { onRetry.invoke() }
            .show()
    }
}
