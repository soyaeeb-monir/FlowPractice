package com.soyaeeb.flowpractice.ui

import com.soyaeeb.flowpractice.model.Posts
import com.soyaeeb.flowpractice.service.PostApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.emptyFlow
import javax.inject.Inject

class PostRepository @Inject constructor(
    private val postApi: PostApi
) {
    suspend fun getAllPosts() : Flow<Posts>{
        val response = postApi.getAllPosts()
        return if (response.isSuccessful) {
            response.body()!!.asFlow()
        } else emptyFlow()
    }
}