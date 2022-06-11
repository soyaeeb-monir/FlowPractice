package com.soyaeeb.flowpractice.api_response

import com.soyaeeb.flowpractice.model.Posts

sealed class PostsUiState{
    data class Success(val post: Posts ): PostsUiState()
    data class Error(val exception: Throwable): PostsUiState()
}