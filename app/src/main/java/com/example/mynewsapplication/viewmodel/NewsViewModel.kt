package com.example.mynewsapplication.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.mynewsapplication.repository.NewsRepository
import com.example.newsapp.database.RoomDatabaseBuilder
import com.example.newsapp.model.DataModel
import com.example.newsapp.model.ResponseDataModel
import java.util.concurrent.Executors

class NewsViewModel( application: Application ,) : AndroidViewModel(application) {

    private val context = getApplication<Application>().applicationContext
    var newsRepository: NewsRepository? = null
    var mutableLiveData: LiveData<ResponseDataModel>? = null
    private val roomDatabaseBuilder = RoomDatabaseBuilder.getInstance(context)

    init {
        newsRepository = NewsRepository()
        mutableLiveData = newsRepository?.mutableList
    }

    /**
     * Calling Function to receive news items based on category , language and country
     * Uses retrofit to call API
     */
    fun getNewsFromApi(category: String , language: String, country: String ) {
        newsRepository?.getData(category ,language , country)
    }

    /**
     * Calling Function to receive news items based on Keyword Search
     * Uses retrofit to call API
     */
    fun getKeywordNewsFromApi( keyword: String) {
        newsRepository?.getKeywordData(keyword)
    }

    /**
     * Function to add data in Room Database upon clicking of Bookmark icon
     */
    fun addBookmark(dataModel: DataModel) {
        Executors.newSingleThreadExecutor().execute {
            roomDatabaseBuilder.dataModelNewsDao().insertNews(
                DataModel(
                    title = dataModel.title,
                    description = dataModel.description,
                    source = dataModel.source,
                    time = dataModel.time,
                    url = dataModel.url,
                    image = dataModel.image,
                    isFav = true
                )
            )
        }
    }

    /**
     * Function to remove data in Room Database upon clicking of Bookmark icon
     */
    fun removeBookmark(dataModel: DataModel) {
        Executors.newSingleThreadExecutor().execute {
            roomDatabaseBuilder.dataModelNewsDao().deleteNews(
                DataModel(
                    title = dataModel.title,
                    description = dataModel.description,
                    source = dataModel.source,
                    time = dataModel.time,
                    url = dataModel.url,
                    image = dataModel.image,
                    isFav = false
                )
            )
        }
    }
}