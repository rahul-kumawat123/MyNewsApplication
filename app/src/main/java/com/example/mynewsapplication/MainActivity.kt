package com.example.mynewsapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.mynewsapplication.repository.NewsRepository
import com.example.newsapp.database.RoomDatabaseBuilder
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //lateinit var viewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val roomDatabase =  RoomDatabaseBuilder.getInstance(this)
//        val newsRepository = NewsRepository(roomDatabase)
//        val viewModelProviderFactory  = NewsViewModelProviderFactory(newsRepository)
//        viewModel = ViewModelProvider(this ,viewModelProviderFactory).get(NewsViewModel::class.java)
        bottomNavigationView.setupWithNavController(newsNavHostFragment.findNavController())

//        if(savedInstanceState == null){
//
//        }
    }
}