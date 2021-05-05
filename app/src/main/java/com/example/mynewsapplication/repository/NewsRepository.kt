package com.example.mynewsapplication.repository

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.mynewsapplication.util.Constants.Companion.KEY
import com.example.newsapp.database.AppRoomDatabase
import com.example.newsapp.model.ResponseDataModel
import com.example.newsapp.rests.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class NewsRepository(
//        val db : AppRoomDatabase
) {
//    suspend fun getData(countryCode : String) = ApiClient.getClient.getData(countryCode)

    val TAG = NewsRepository::class.java.simpleName
    val mutableList: MutableLiveData<ResponseDataModel> = MutableLiveData()
    val errorString: MutableLiveData<String> = MutableLiveData()


    fun getData(category: String){
        val call = ApiClient.getClient.getCategorisedData(KEY  , "en" , category  )
        call.enqueue(object : Callback<ResponseDataModel> {
            override fun onResponse(
                    call: Call<ResponseDataModel>,
                    response: Response<ResponseDataModel>
            ) {
                if(response.isSuccessful){
                    Log.i(TAG, response.body().toString())
                    mutableList.postValue(response.body())
//                    Toast.makeText(this,"${response.body()?.data.}")
                }
            }

            override fun onFailure(call: Call<ResponseDataModel>, t: Throwable) {
                Log.e(TAG,"Error is ${t.localizedMessage}")
//                Context.showToast("Some Error Occurred. Please Check Internet Connection")

            }
        })
    }


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
            }

            override fun onFailure(call: Call<ResponseDataModel>, t: Throwable) {
                Log.e(TAG, "Error is ${t.localizedMessage}")
//                showToast("Some Error Occurred. Please Check Internet Connection")
            }
        })
    }
}