package com.amirjaveed.kotlinbasewithmvvm.data.remote


import com.amirjaveed.kotlinbasewithmvvm.data.models.PostsResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("posts")
    suspend fun getPosts(): Response<PostsResponse>
}