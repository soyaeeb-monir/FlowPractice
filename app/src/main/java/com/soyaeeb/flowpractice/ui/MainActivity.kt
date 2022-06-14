package com.soyaeeb.flowpractice.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.soyaeeb.flowpractice.adapter.PostAdapter
import com.soyaeeb.flowpractice.ui_state.PostsUiState
import com.soyaeeb.flowpractice.databinding.ActivityMainBinding
import com.soyaeeb.flowpractice.model.Posts
import com.soyaeeb.flowpractice.ui.data.PostViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), PostAdapter.OnAdapterItemClick {
    private lateinit var binding: ActivityMainBinding
    private  var postAdapter : PostAdapter = PostAdapter()
    @Inject lateinit var postViewModel: PostViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = postAdapter
        postAdapter.setOnItemClickLister(this@MainActivity)


        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                postViewModel.uiState.collect{ postState ->
                    when (postState) {
                        is PostsUiState.Success -> {
                            postAdapter.setPostList(postState.postList)
                        }
                        is PostsUiState.Error -> {}
                        is PostsUiState.ShowToast -> {
                            Toast.makeText(this@MainActivity,"Clicked Item is ${postState.id}",Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }

        }
    }

    override fun onItemClick(post: Posts) {
        postViewModel.itemClickAction.invoke(PostUiAction.ItemClicked(post.id))
    }
}