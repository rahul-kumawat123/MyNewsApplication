package com.example.mynewsapplication

import android.app.Application
import android.content.Context

class MyNewsApplication : Application(){

    init {
        myNewsApplication = this

    }


    companion object{
        private lateinit var myNewsApplication: MyNewsApplication

        fun getApplicationContext(): Context{
            return myNewsApplication.applicationContext
        }
    }

}
