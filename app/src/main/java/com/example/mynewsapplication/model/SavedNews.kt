package com.example.newsapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "News_table")
data class SavedNews(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "News_Id")
    var id :Int? = null,

    @ColumnInfo(name= "News_title")
    var news_title: String,

//    @ColumnInfo(name = "News_author")
//    var news_author :  String,

    @ColumnInfo(name = "News_description")
    var news_desc: String?,

    @ColumnInfo(name = "News_url")
    var news_url: String?,

    @ColumnInfo(name = "News_image")
    var news_image : String,

    @ColumnInfo(name = "News_source")
    var news_source: String,

//    @ColumnInfo(name = "News_country")
//    val news_country: String,

    @ColumnInfo(name = "news_publishedTime")
    var news_time: String? ,

    var isFav: Boolean = false
)
