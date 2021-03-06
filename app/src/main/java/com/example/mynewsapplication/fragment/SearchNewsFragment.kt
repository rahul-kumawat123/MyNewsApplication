package com.example.mynewsapplication.fragment

import android.os.Bundle
import android.util.Log
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mynewsapplication.adapter.ItemAdapter
import com.example.mynewsapplication.activity.MainActivity
import com.example.mynewsapplication.viewmodel.NewsViewModel
import com.example.mynewsapplication.R
import kotlinx.android.synthetic.main.fragment_search_news.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SearchNewsFragment: Fragment(R.layout.fragment_search_news) {

    lateinit var viewModel: NewsViewModel
    val TAG = SearchNewsFragment::class.java.simpleName

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(this).get(NewsViewModel::class.java)

        //using coroutine to give a slight delay while searching for news
        var job: Job? = null
        etSearch.addTextChangedListener { editable ->
            job?.cancel()
            job = MainScope().launch {
                delay(500L)
                editable.let {
                    if(editable.toString().isNotEmpty()){
                        //displaying news using keyword search
                        viewModel.getKeywordNewsFromApi(editable.toString())
                    }
                }
            }
        }

        viewModel.mutableLiveData?.observe(viewLifecycleOwner , Observer { dataList ->
            rvSearchNews.also {
                it.layoutManager = LinearLayoutManager(requireContext())
                it.adapter = ItemAdapter(activity as MainActivity,dataList.data)
                //checking if news item is Bookmarked or not and adding or removing to room database
                { newsData ->
                    if(newsData.isFav){
                        viewModel.addBookmark(newsData)
                    }else{
                        viewModel.removeBookmark(newsData)
                    }
                }
                Log.i(TAG, dataList.data.toString())
            }
        })
    }

}