package com.example.sampleapicall.viewmodels

import data.PostDtoItem
import data.network.PostRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class PostViewModel : BaseViewModel() {

    private  val _postListData = MutableStateFlow<Result<List<PostDtoItem>>>(Result.Loading)
    val posListData = _postListData.asStateFlow()
    init {
         withProgress(_postListData) {
              PostRepository().getPosts()
         }
    }
}