package com.example.mynewsapplication.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mynewsapplication.activity.MainActivity
import com.example.mynewsapplication.viewmodel.NewsViewModel
import com.example.mynewsapplication.R
import com.example.mynewsapplication.adapter.ItemAdapter
import kotlinx.android.synthetic.main.fragment_live_news.*

class LiveNewsFragment : Fragment(R.layout.fragment_live_news) {

    lateinit var viewModel: NewsViewModel
    private var newsAdapter: ItemAdapter? = null
    val TAG = LiveNewsFragment::class.java.simpleName
    private val args: LiveNewsFragmentArgs by navArgs()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(this).get(NewsViewModel::class.java)

        viewModel.getNewsFromApi(args.category , args.language , args.country)
        viewModel.mutableLiveData?.observe(viewLifecycleOwner , Observer { dataList ->
            rvLiveNews.also {
                it.layoutManager = LinearLayoutManager(requireContext())
                it.adapter = ItemAdapter(activity as MainActivity, dataList.data  )
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