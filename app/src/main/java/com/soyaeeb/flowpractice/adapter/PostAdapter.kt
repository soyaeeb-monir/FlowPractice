package com.soyaeeb.flowpractice.adapter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.soyaeeb.flowpractice.databinding.SinglePostBinding
import com.soyaeeb.flowpractice.model.Posts

class PostAdapter(
    private val postList: ArrayList<Posts>
    ) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(SinglePostBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }
    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        if(position !=0){
            val currentPost = postList[position]
            holder.binding.apply {
                textViewTitle.text = currentPost.title
                textViewBody.text = currentPost.body
            }
        }
    }

    override fun getItemCount() = postList.size

   inner class PostViewHolder(val binding: SinglePostBinding) : RecyclerView.ViewHolder(binding.root)


}