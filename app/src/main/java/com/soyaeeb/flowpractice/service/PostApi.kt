package com.soyaeeb.flowpractice.service
import com.soyaeeb.flowpractice.model.Posts
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET

interface PostApi {

   @GET("/posts")
  suspend fun getAllPosts() : Response<List<Posts>>
}



