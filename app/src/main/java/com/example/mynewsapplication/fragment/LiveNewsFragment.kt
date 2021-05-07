package com.example.mynewsapplication.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mynewsapplication.MainActivity
import com.example.mynewsapplication.NewsViewModel
import com.example.mynewsapplication.R
import com.example.mynewsapplication.ItemAdapter
import kotlinx.android.synthetic.main.fragment_live_news.*

class LiveNewsFragment : Fragment(R.layout.fragment_live_news) {

    lateinit var viewModel: NewsViewModel
    private var newsAdapter: ItemAdapter? = null
    val TAG = LiveNewsFragment::class.java.simpleName
    private val args: LiveNewsFragmentArgs by navArgs()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(this).get(NewsViewModel::class.java)

        newsAdapter?.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("webNews", it)
            }
            findNavController().navigate(
                R.id.action_liveNewsFragment_to_webNewsFragment,
                bundle
            )
        }

        viewModel.getNewsFromApi(args.category)
        viewModel.mutableLiveData?.observe(viewLifecycleOwner , Observer { dataList ->
            rvLiveNews.also {
                it.layoutManager = LinearLayoutManager(requireContext())
                it.adapter = ItemAdapter(activity as MainActivity, dataList.data  )

                   // onItemClick = findNavController().navigate(R.id.action_liveNewsFragment_to_webNewsFragment))
//                { newsData ->
//                    if(newsData.isFav){
//                        viewModel.addBookmark(newsData)
//                    }else{
//                        viewModel.removeBookmark(newsData)
//                    }
//                }
                Log.i(TAG, dataList.data.toString())
            }
        })


    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //viewModel = (activity as MainActivity).viewModel

//        viewModel.liveNews.observe(viewLifecycleOwner , Observer { response ->
//            when(response){
//                is Resource.Success -> {
//                    response.data?.let{ newsResponse ->
//                        newsAdapter
//
//                    }
//                }
//            }
//
//        })

    }
}