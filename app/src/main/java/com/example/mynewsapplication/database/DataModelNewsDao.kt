package com.example.newsapp.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.newsapp.model.DataModel

@Dao
interface DataModelNewsDao {

    //method used to show all data from database
    @Query("Select * from News_table")
    fun getAllNews(): List<DataModel>

    //method used to insert data in database
    @Insert()
    fun insertNews(dataModel: DataModel)

    //method used to delete data in database
    @Delete
    fun deleteNews(dataModel: DataModel)
}