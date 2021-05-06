package com.example.mynewsapplication.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mynewsapplication.ItemAdapter
import com.example.mynewsapplication.MainActivity
import com.example.mynewsapplication.NewsViewModel
import com.example.mynewsapplication.R
import kotlinx.android.synthetic.main.fragment_category.*
import kotlinx.android.synthetic.main.fragment_live_news.*

class CategoryFragment: Fragment(R.layout.fragment_category) {
    lateinit var viewModel: NewsViewModel
    val TAG = CategoryFragment::class.java.simpleName
    lateinit var category: String


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(NewsViewModel::class.java)

        setUpClickListeners()
    }

    private fun setUpClickListeners() {
        businessBTN.setOnClickListener {
            category = "business"
            navigateToLiveNewsWithCategory(category)
        }

        entertainmentBTN.setOnClickListener {
            category = "entertainment"
            navigateToLiveNewsWithCategory(category)
        }

        healthBTN.setOnClickListener {
            category = "health"
            navigateToLiveNewsWithCategory(category)
        }

        scienceBTN.setOnClickListener {
            category = "science"
            navigateToLiveNewsWithCategory(category)
        }

        sportsBTN.setOnClickListener {
            category = "sports"
            navigateToLiveNewsWithCategory(category)
        }

        technologyBTN.setOnClickListener {
            category = "technology"
            navigateToLiveNewsWithCategory(category)
        }
    }

    private fun navigateToLiveNewsWithCategory(category: String){
        val action = CategoryFragmentDirections.actionCategoryFragmentToLiveNewsFragment(category)
        findNavController().navigate(action)
    }

}