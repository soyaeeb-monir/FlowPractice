package com.soyaeeb.flowpractice.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.soyaeeb.flowpractice.databinding.SinglePostBinding
import com.soyaeeb.flowpractice.model.Posts

class PostAdapter: RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    lateinit var listener : OnAdapterItemClick
    private var postList : List<Posts> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(
            SinglePostBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val currentPost = postList[position]
        holder.binding.apply {
            textViewTitle.text = currentPost.title
            textViewBody.text = currentPost.body

            holder.itemView.setOnClickListener {
                listener.onItemClick(postList[position])
            }
        }
    }

    override fun getItemCount() = postList.size

    inner class PostViewHolder(val binding: SinglePostBinding) :
        RecyclerView.ViewHolder(binding.root)


    interface OnAdapterItemClick{
        fun onItemClick(post : Posts)
    }

    fun setOnItemClickLister(listener: OnAdapterItemClick){
        this.listener = listener
    }

    fun setPostList(postList : List<Posts>){
        this.postList = postList
        notifyDataSetChanged()
    }


}