package com.example.newsapp.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.newsapp.model.DataModel

@Dao
interface DataModelNewsDao {

    @Query("Select * from News_table")
    fun getAllNews(): List<DataModel>

    @Insert()
    fun insertNews(dataModel: DataModel)

    @Delete
    fun deleteNews(dataModel: DataModel)
}