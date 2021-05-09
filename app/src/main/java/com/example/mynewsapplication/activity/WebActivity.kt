package com.example.mynewsapplication.activity

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.example.mynewsapplication.R
import com.example.mynewsapplication.showToast
import com.example.mynewsapplication.viewmodel.BookmarksViewModel

class WebActivity : AppCompatActivity() {

    lateinit var bookmarksViewModel: BookmarksViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)
        val TAG = WebActivity::class.java.simpleName

        if(isConnectionOn(applicationContext)){
            val webview = findViewById<WebView>(R.id.newsWebView)
            webview?.webViewClient = MyWebViewClient()
            val urlData = intent.getStringExtra("url")

            //showToast("url is $urlData")
            Log.i(TAG , "url is $urlData")
            if (intent.hasExtra("url")) {

                if (intent.getStringExtra("url")!!.isNotBlank()) {
                    //loading url to our webView
                    webview?.loadUrl(intent.getStringExtra("url")!!)
                }
            }
        }
        else{
            showToast("Some Error Occurred. Please Check Internet Connection ")
        }
    }

    class MyWebViewClient : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            view.loadUrl(url)
            return true
        }
    }

    /**
     *Function to check Internet Connection of the Application
     */
    private fun isConnectionOn(context: Context): Boolean{
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            val activeNetwork = connectivityManager.activeNetwork ?:  return false
            val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
            return when{
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        }else{
            connectivityManager.activeNetworkInfo?.run {
                return when(type) {
                    ConnectivityManager.TYPE_WIFI -> true
                    ConnectivityManager.TYPE_MOBILE -> true
                    ConnectivityManager.TYPE_ETHERNET -> true
                    else -> false
                }
            }
        }
        return false
    }

}