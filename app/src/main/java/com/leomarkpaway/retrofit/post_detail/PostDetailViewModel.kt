package com.leomarkpaway.retrofit.post_detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.leomarkpaway.retrofit.api.ApiClient
import com.leomarkpaway.retrofit.model.AllPost
import com.leomarkpaway.retrofit.model.PostDetails
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostDetailViewModel : ViewModel() {
    private val apiClient by lazy { ApiClient.apiService }

    private val _postDetail = MutableLiveData<PostDetails>()
    val postDetail: LiveData<PostDetails> = _postDetail

    fun getPostDetailById(id: Int) {
        val postDetailCallback = apiClient.getPostById(id)
        postDetailCallback.enqueue(object : Callback<PostDetails> {
            override fun onResponse(call: Call<PostDetails>, response: Response<PostDetails>) {
                if (response.isSuccessful) {
                    val response = response.body()
                    _postDetail.value = response
                    Log.d("qwe", "res $response")
                } else {
                    // Handle error
                    Log.d("qwe", "error")
                }
            }

            override fun onFailure(call: Call<PostDetails>, t: Throwable) {
                // Handle failure
                Log.d("qwe", "onFailure $call - $t")
            }
        })
    }
}