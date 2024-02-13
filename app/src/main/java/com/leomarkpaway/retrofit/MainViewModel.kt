package com.leomarkpaway.retrofit

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.leomarkpaway.retrofit.api.ApiClient
import com.leomarkpaway.retrofit.model.AllPost
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel: ViewModel() {
    private val apiClient by lazy { ApiClient.apiService }

    private val _allPost = MutableLiveData<AllPost>()
    val allPost: LiveData<AllPost> = _allPost

    fun getAllPost() {
        val allPostCallBack = apiClient.getAllPost()

        allPostCallBack.enqueue(object : Callback<AllPost> {
            override fun onResponse(call: Call<AllPost>, response: Response<AllPost>) {
                if (response.isSuccessful) {
                    val response = response.body()
                    _allPost.value = response
                } else {
                    // Handle error
                    Log.d("qwe", "error")
                }
            }

            override fun onFailure(call: Call<AllPost>, t: Throwable) {
                // Handle failure
                Log.d("qwe", "onFailure $call - $t")
            }
        })

    }
}