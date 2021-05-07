package com.example.mynewsapplication

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.newsapp.database.RoomDatabaseBuilder
import com.example.newsapp.model.SavedNews
import java.util.concurrent.Executors

class BookmarksViewModel(application: Application): AndroidViewModel(application) {

    private val roomLiveData: MutableLiveData<List<SavedNews>> = MutableLiveData<List<SavedNews>>()
    private val context = getApplication<Application>().applicationContext
    private val roomDatabaseBuilder = RoomDatabaseBuilder.getInstance(context)

    fun getSavedDataFromDatabase():MutableLiveData<List<SavedNews>>{
            Executors.newSingleThreadExecutor().execute {
                val list = roomDatabaseBuilder.savedNewsDao().getAllNews()
                roomLiveData.postValue(list)
            }
        return roomLiveData
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