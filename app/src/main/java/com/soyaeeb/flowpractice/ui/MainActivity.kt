package com.soyaeeb.flowpractice.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.soyaeeb.flowpractice.adapter.PostAdapter
import com.soyaeeb.flowpractice.api_response.PostsUiState
import com.soyaeeb.flowpractice.databinding.ActivityMainBinding
import com.soyaeeb.flowpractice.model.Posts
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var postAdapter : PostAdapter

    private val  postList : ArrayList<Posts> = ArrayList()

    @Inject lateinit var postViewModel: PostViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recyclerView.setHasFixedSize(true)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                postViewModel.uiState.collect{ postState ->
                    when (postState) {
                        is PostsUiState.Success -> {
                            postList.add(postState.post)
                            postAdapter = PostAdapter(postList)
                            binding.recyclerView.adapter = postAdapter
                            postAdapter.notifyDataSetChanged()
                        }
                        is PostsUiState.Error -> {}
                    }
                }
            }

        }

    }
}