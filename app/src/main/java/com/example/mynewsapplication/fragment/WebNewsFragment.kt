package com.example.mynewsapplication.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.mynewsapplication.MainActivity
import com.example.mynewsapplication.NewsViewModel
import com.example.mynewsapplication.R
import kotlinx.android.synthetic.main.fragment_web_news.*

class WebNewsFragment: Fragment(R.layout.fragment_web_news) {

    lateinit var viewModel: NewsViewModel
    private val args: WebNewsFragmentArgs by navArgs()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(NewsViewModel::class.java)

        val webNews = args.webNews
        webView.apply {
            webViewClient = WebViewClient()
            loadUrl(webNews.url)
        }

//        val intentData = requireActivity()

//        webView.webViewClient = object : WebViewClient(){
//            override fun shouldOverrideUrlLoading(
//                view: WebView?,
//                url: String?
//            ): Boolean {
//               if (url!=null){
//                   view?.loadUrl(url)
//               }
//                return true
//            }
//        }
//        if (intentData.intent.hasExtra("news_url")){
//            if(intentData.intent.getStringExtra("news_url").isNullOrBlank()){
//                intentData.intent.getStringExtra("news_url")?.let { webView.loadUrl(it) }
//            }
//        if(arguments != null){
//            val url = arguments?.getString("url")
//            if (url !=null){
//                webView.loadUrl(url)
//            }
//        }
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        val webview = findViewById<WebView>(R.id.newsWebView)
//    }
}