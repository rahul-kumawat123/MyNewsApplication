package com.example.mynewsapplication.fragment

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mynewsapplication.*
import com.example.mynewsapplication.activity.MainActivity
import com.example.mynewsapplication.adapter.ItemAdapter
import com.example.mynewsapplication.viewmodel.BookmarksViewModel
import kotlinx.android.synthetic.main.fragment_bookmark.*

class BookmarksFragment: Fragment(R.layout.fragment_bookmark) {

    lateinit var bookmarksViewModel: BookmarksViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bookmarksViewModel = ViewModelProvider(this).get(BookmarksViewModel::class.java)

        bookmarksViewModel.getSavedDataFromDatabase()
        bookmarksViewModel.roomLiveData.observe(viewLifecycleOwner , Observer { dataList ->
            rvSavedNews.also {
                it.layoutManager = LinearLayoutManager(requireContext())
                it.adapter = ItemAdapter(activity as MainActivity, dataList)
                { roomData ->
                    if (roomData.isFav){
                        bookmarksViewModel.addBookmark(roomData)
                    }else{
                        bookmarksViewModel.removeBookmark(roomData)
                    }
                }
                if (dataList.isEmpty()){
                    noBookmarksTV.isVisible = true
                    noBookmarksTV.text = "You have no Bookmarks till now"
                }
            }

        })
    }
}