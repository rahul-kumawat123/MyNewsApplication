package com.example.mynewsapplication.activity

import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.example.mynewsapplication.R
import com.example.mynewsapplication.viewmodel.BookmarksViewModel

class WebActivity : AppCompatActivity() {

    lateinit var bookmarksViewModel: BookmarksViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)
        val TAG = WebActivity::class.java.simpleName

        val webview = findViewById<WebView>(R.id.newsWebView)
        webview?.webViewClient = MyWebViewClient()
        val urlData = intent.getStringExtra("url")

        //showToast("url is $urlData")
        Log.i(TAG , "url is $urlData")
        if (intent.hasExtra("url")) {

            if (intent.getStringExtra("url")!!.isNotBlank()) {
                webview?.loadUrl(intent.getStringExtra("url")!!)
                //showToast("url is ${intent.getStringExtra("url")}")
            }
        }
    }

    class MyWebViewClient : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            view.loadUrl(url)
            return true
        }
    }
}