package com.cherish.myweatherapp.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.Log
import android.widget.Toast
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.lang.Exception
import java.net.InetSocketAddress
import java.net.Socket
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.Callable

class AppUtils {
    companion object {
        lateinit var toast: Toast
        fun showToast(context: Context, message: String) {
            if (toast != null) toast.cancel()
            toast = Toast.makeText(context, message, Toast.LENGTH_LONG)
            toast.show()
        }

        fun checkConnection(context: Context): Boolean {
            val connectManager: ConnectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo: NetworkInfo = connectManager.activeNetworkInfo
            return networkInfo != null && networkInfo.isConnected
        }

        fun getDateString(date: Long): String {
            return SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(Date(date * 1000L))
        }

        fun getTimeString(date: String): String {
            var dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
            dateFormat.timeZone = TimeZone.getTimeZone("UTC")
            try {
                val newDate: Date = dateFormat.parse(date)
                dateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
                dateFormat.timeZone = TimeZone.getDefault()
                return dateFormat.format(newDate)

            } catch (exception: ParseException) {
                exception.printStackTrace()
                return ""

            }
        }

        fun getDay(date: String): String {
            var dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
            dateFormat.timeZone = TimeZone.getTimeZone("UTC")
            try {
                val newDate: Date = dateFormat.parse(date)
                dateFormat = SimpleDateFormat("EEEE", Locale.getDefault())
                dateFormat.timeZone = TimeZone.getDefault()
                return dateFormat.format(newDate)
            } catch (e: ParseException) {
                e.printStackTrace()
                return ""
            }
        }


        fun hasInternet(): Single<Boolean> {
            return Single.fromCallable<Boolean> {
                try {
                    val timeoutMs = 2000
                    val socket = Socket()
                    socket.connect(InetSocketAddress("api.openweathermap.org", 443), timeoutMs)
                    socket.close()
                    Log.i("RESPONSE","RESPONSE")
                     true
                } catch (e: Exception) {
                    e.printStackTrace()
                    Log.i("ERROR","Error")
                     false
                }
            }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }

    }
}