package com.amirjaveed.kotlinbasewithmvvm.ui.firstfragment

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amirjaveed.kotlinbasewithmvvm.data.models.PostsResponse
import com.amirjaveed.kotlinbasewithmvvm.data.remote.Resource
import com.amirjaveed.kotlinbasewithmvvm.data.remote.reporitory.MainRepository
import com.amirjaveed.kotlinbasewithmvvm.utils.NetworkHelper
import kotlinx.coroutines.launch


class FirstViewModel @ViewModelInject constructor(
    private val mainRepository: MainRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {


    private val _posts = MutableLiveData<Resource<PostsResponse>>()
    val postsData: LiveData<Resource<PostsResponse>>
        get() = _posts


    fun fetchPostsFromApi() {
        viewModelScope.launch {
            _posts.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                mainRepository.getPosts().let {
                    if (it.isSuccessful) {
                        _posts.postValue(Resource.success(it.body()!!))
                    } else _posts.postValue(Resource.error(it.message(), null))
                }
            } else _posts.postValue(Resource.error("No internet connection", null))
        }
    }

}