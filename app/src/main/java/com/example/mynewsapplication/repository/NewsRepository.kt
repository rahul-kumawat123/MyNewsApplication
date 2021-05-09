package com.example.mynewsapplication.repository

import android.app.ProgressDialog
import android.content.Context
import android.util.Log
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.lifecycle.MutableLiveData
import com.example.mynewsapplication.activity.MainActivity
import com.example.mynewsapplication.util.Constants.Companion.KEY
import com.example.newsapp.database.AppRoomDatabase
import com.example.newsapp.model.ResponseDataModel
import com.example.newsapp.rests.ApiClient
import org.intellij.lang.annotations.Language
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class NewsRepository() {

    val TAG = NewsRepository::class.java.simpleName
    val mutableList: MutableLiveData<ResponseDataModel> = MutableLiveData()

    /**
     * Function to receive news items based on category , language and country
     * Uses retrofit to call API
     */
    fun getData(category: String ,language: String , country: String){

        val call = ApiClient.getClient.getCategorisedData(KEY  , category , language , country  )
        call.enqueue(object : Callback<ResponseDataModel> {
            override fun onResponse(
                    call: Call<ResponseDataModel>,
                    response: Response<ResponseDataModel>
            ) {
                if(response.isSuccessful){
                    Log.i(TAG, response.body().toString())
                    mutableList.postValue(response.body())
                }else{
                    Log.e(TAG , "There is a problem in Establishing Connection")
                }
            }

            override fun onFailure(call: Call<ResponseDataModel>, t: Throwable) {
                Log.e(TAG,"Error is ${t.localizedMessage}")
            }
        })
    }

    /**
     * Function to receive news items based on Keyword Search
     * Uses retrofit to call API
     */
    fun getKeywordData( keyword: String){
        val call = ApiClient.getClient.getSearchData(KEY, "en",  keyword)
        call.enqueue(object : Callback<ResponseDataModel> {
            override fun onResponse(
                    call: Call<ResponseDataModel>,
                    response: Response<ResponseDataModel>
            ) {
                if (response.isSuccessful) {
                    Log.i(TAG, response.body().toString())
                    mutableList.postValue(response.body())
                }
                else{
                    Log.e(TAG , "There is a problem in Establishing Connection")
                }
            }

            override fun onFailure(call: Call<ResponseDataModel>, t: Throwable) {
                Log.e(TAG, "Error is ${t.localizedMessage}")
            }
        })
    }


}