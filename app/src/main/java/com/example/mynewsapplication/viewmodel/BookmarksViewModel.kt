package com.example.mynewsapplication.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.newsapp.database.RoomDatabaseBuilder
import com.example.newsapp.model.DataModel
import java.util.concurrent.Executors

class BookmarksViewModel(application: Application): AndroidViewModel(application) {

    var roomLiveData: MutableLiveData<List<DataModel>> = MutableLiveData<List<DataModel>>()
    private val context = getApplication<Application>().applicationContext
    private val roomDatabaseBuilder = RoomDatabaseBuilder.getInstance(context)

    /**
     * Function to Retrieve Data from Room Databse
     */
    fun getSavedDataFromDatabase():MutableLiveData<List<DataModel>>{
            Executors.newSingleThreadExecutor().execute {
                val list = roomDatabaseBuilder.dataModelNewsDao().getAllNews()
                roomLiveData.postValue(list)
            }
        return roomLiveData
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