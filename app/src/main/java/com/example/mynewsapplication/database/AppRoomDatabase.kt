package com.example.newsapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.newsapp.model.DataModel


@Database(entities = [DataModel::class] , version = 5)
abstract class AppRoomDatabase: RoomDatabase() {
    abstract fun dataModelNewsDao(): DataModelNewsDao
}