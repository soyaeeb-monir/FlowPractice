package com.soyaeeb.flowpractice.ui.data
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soyaeeb.flowpractice.ui_state.PostsUiState
import com.soyaeeb.flowpractice.ui.PostUiAction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val postRepository: PostRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<PostsUiState>(PostsUiState.Success(emptyList()))
    val uiState: StateFlow<PostsUiState> = _uiState

    val itemClickAction: (PostUiAction) -> Unit


    init {
        viewModelScope.launch {
            postRepository.getAllPost().collect { posts ->
                _uiState.value = PostsUiState.Success(posts)
            }
        }

        itemClickAction = { postUiAction ->
            when(postUiAction){
                is PostUiAction.ItemClicked -> {
                    _uiState.value = PostsUiState.ShowToast(postUiAction.id)
                }

            }
        }
    }

}
