package com.example.mynewsapplication.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mynewsapplication.viewmodel.NewsViewModel
import com.example.mynewsapplication.R
import kotlinx.android.synthetic.main.fragment_category.*

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