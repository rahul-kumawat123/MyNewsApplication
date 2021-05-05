package com.example.mynewsapplication

import android.app.Application
import androidx.lifecycle.*
import com.example.mynewsapplication.repository.NewsRepository
import com.example.newsapp.database.RoomDatabaseBuilder
import com.example.newsapp.model.ResponseDataModel
import com.example.newsapp.model.SavedNews
import java.util.concurrent.Executors

class NewsViewModel( application: Application ,
//        val newsRepo : NewsRepository
) : AndroidViewModel(application) {
//
//    val liveNews: MutableLiveData<Resource<ResponseDataModel>> = MutableLiveData()
//
//    fun getData(countryCode: String) = viewModelScope.launch {
//        liveNews.postValue(Resource.Loading())
//
//        val response = newsRepo.getData(countryCode)
//        liveNews.postValue(handleLiveNewsResponse(response))
//    }
//
//    private fun handleLiveNewsResponse(response: Response<ResponseDataModel>) : Resource<ResponseDataModel>{
//        if (response.isSuccessful){
//            response.body()?.let { resultResponse ->
//                return (Resource.Success(resultResponse))
//            }
//        }
//        return Resource.Error(response.message())
//    }

    private val context = getApplication<Application>().applicationContext
    var newsRepository: NewsRepository? = null
    var mutableLiveData: LiveData<ResponseDataModel>? = null
    private val roomDatabaseBuilder = RoomDatabaseBuilder.getInstance(context)

    init {
        newsRepository = NewsRepository()
        mutableLiveData = newsRepository?.mutableList
    }


    fun getNewsFromApi(category: String) {
        newsRepository?.getData(category)
    }


    fun getKeywordNewsFromApi(category: String, keyword: String) {
        newsRepository?.getKeywordData(category, keyword)
    }


    fun addBookmark(savedNews: SavedNews) {
        Executors.newSingleThreadExecutor().execute {
            roomDatabaseBuilder.savedNewsDao().insertNews(
                    SavedNews(
                            news_title = savedNews.news_title,
                            news_desc = savedNews.news_desc,
                            news_source = savedNews.news_source,
                            news_time = savedNews.news_time,
                            news_url = savedNews.news_url,
                            news_image = savedNews.news_image,
                            isFav = true )
            )
        }
    }


    fun removeBookmark(savedNews: SavedNews) {
        Executors.newSingleThreadExecutor().execute {
            roomDatabaseBuilder.savedNewsDao().deleteNews(
                    SavedNews(
                            news_title = savedNews.news_title,
                            news_desc = savedNews.news_desc,
                            news_source = savedNews.news_source,
                            news_time = savedNews.news_time,
                            news_url = savedNews.news_url,
                            news_image = savedNews.news_image,
                            isFav = false )
            )
        }
    }
}