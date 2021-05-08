package com.example.newsapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "News_table")
data class DataModel(


//    @ColumnInfo(name = "News_Id")
//    var id :Int? = null,
    @PrimaryKey
    @SerializedName("title")
    var title : String,

    @SerializedName("url")
    var url: String,

    @SerializedName("description")
    var description: String,

    @SerializedName("image")
    var image: String,

    @SerializedName("source")
    var source: String,

    @SerializedName("published_at")
    var time: String,

    var isFav: Boolean = false

)
