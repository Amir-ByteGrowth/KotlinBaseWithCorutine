package com.amirjaveed.kotlinbasewithmvvm.data.remote.reporitory

import com.amirjaveed.kotlinbasewithmvvm.data.local.db.AppDao
import com.amirjaveed.kotlinbasewithmvvm.data.remote.ApiService
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val apiService: ApiService,
    localDataSource: AppDao
) {

    suspend fun getPosts() = apiService.getPosts()

}