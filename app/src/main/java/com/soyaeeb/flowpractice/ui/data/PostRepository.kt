package com.soyaeeb.flowpractice.ui.data

import com.soyaeeb.flowpractice.model.Posts
import com.soyaeeb.flowpractice.service.PostApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PostRepository @Inject constructor(
    private val postApi: PostApi
) {
    suspend fun getAllPost() : Flow<List<Posts>> = flow {
        val response = postApi.getAllPosts()
        emit(response.body()!!)
    }

}