package com.soyaeeb.flowpractice.ui_state
import com.soyaeeb.flowpractice.model.Posts

sealed class PostsUiState{
    data class Success(val postList: List<Posts> ): PostsUiState()
    data class Error(val exception: Throwable): PostsUiState()
    data class ShowToast(val id: Int): PostsUiState()
}

