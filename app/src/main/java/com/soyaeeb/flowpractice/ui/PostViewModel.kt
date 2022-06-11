package com.soyaeeb.flowpractice.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soyaeeb.flowpractice.api_response.PostsUiState
import com.soyaeeb.flowpractice.model.Posts
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val postRepository: PostRepository
): ViewModel() {

    private val _uiState = MutableStateFlow(PostsUiState.Success(Posts(0,0,"","")))
    val uiState: StateFlow<PostsUiState> = _uiState

    init {
        viewModelScope.launch {
            postRepository.getAllPosts()
                .collect { posts ->
                    _uiState.value = PostsUiState.Success(posts)
                }
        }
    }
}