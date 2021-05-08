package com.example.mynewsapplication.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.mynewsapplication.repository.NewsRepository
import com.example.newsapp.database.RoomDatabaseBuilder
import com.example.newsapp.model.DataModel
import com.example.newsapp.model.ResponseDataModel
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


    fun getNewsFromApi(category: String , language: String, country: String ) {
        newsRepository?.getData(category ,language , country)
    }


    fun getKeywordNewsFromApi( keyword: String) {
        newsRepository?.getKeywordData(keyword)
    }

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