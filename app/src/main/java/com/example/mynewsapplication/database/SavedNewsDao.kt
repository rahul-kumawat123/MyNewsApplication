package com.example.newsapp.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.newsapp.model.SavedNews

@Dao
interface SavedNewsDao {

    @Query("Select * from News_table")
    fun getAllNews(): List<SavedNews>

    @Insert(/*onConflict = OnConflictStrategy.REPLACE*/)
    fun insertNews(savedNews: SavedNews)

    @Delete
    fun deleteNews(savedNews: SavedNews)
}