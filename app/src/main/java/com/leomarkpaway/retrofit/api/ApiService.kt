package com.leomarkpaway.retrofit.api

import com.leomarkpaway.retrofit.model.AllPost
import com.leomarkpaway.retrofit.model.PostDetails
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("posts")
    fun getAllPost(): Call<AllPost>

    @GET("posts/{id}")
    fun getPostById(@Path("id") postId: Int): Call<PostDetails>
}