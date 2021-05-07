package com.example.mynewsapplication

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.example.mynewsapplication.fragment.LiveNewsFragment
import com.example.mynewsapplication.util.showToast
import kotlinx.android.synthetic.main.activity_web.*
import kotlinx.android.synthetic.main.fragment_web_news.*

class WebActivity : AppCompatActivity() {

    lateinit var bookmarksViewModel: BookmarksViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)
        val TAG = WebActivity::class.java.simpleName

        //webView.settings.javaScriptEnabled = true
//        webView.webViewClient = object : WebViewClient(){
//            override fun shouldOverrideUrlLoading(
//                view: WebView?,
//                url: String?
//            ): Boolean {
//                if (url!=null){
//                    view?.loadUrl(url)
//                }
//                return true
//            }
//        }
//        if (intent.hasExtra("news_url")) {
//            if (intent.getStringExtra("news_url").isNullOrBlank()) {
//                intent.getStringExtra("news_url")?.let { webView.loadUrl(it) }
//            }
//        }

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
//        fab1.setOnClickListener {
//            bookmarksViewModel.addBookmark()
//        }

    }

    class MyWebViewClient : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            view.loadUrl(url)
            return true
        }
    }
}