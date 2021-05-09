package com.example.mynewsapplication.repository

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.mynewsapplication.R
import com.example.mynewsapplication.MyNewsApplication
import com.example.mynewsapplication.showToast
import com.example.mynewsapplication.util.Constants.Companion.KEY
import com.example.newsapp.model.ResponseDataModel
import com.example.newsapp.rests.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.net.ConnectException
import java.net.UnknownHostException

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
                if (t is UnknownHostException
                    || t is ConnectException
                    || t is IOException
                ) {
                    MyNewsApplication.getApplicationContext().showToast("Please check Internet Connection")
                }
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
                if (t is UnknownHostException
                    || t is ConnectException
                    || t is IOException
                ) {
                    MyNewsApplication.getApplicationContext().showToast("Please check Internet Connection")
                }
            }
        })
    }


}