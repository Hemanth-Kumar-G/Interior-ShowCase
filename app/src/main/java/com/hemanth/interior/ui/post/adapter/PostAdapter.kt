package com.hemanth.interior.ui.post.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hemanth.interior.databinding.PostItemBinding


class PostAdapter(private val postList: ArrayList<String>) :
    RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    var onItemSelectedListener: ((post: String) -> Unit)? = null

    class PostViewHolder(val binding: PostItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = PostItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding)
    }

    override fun getItemCount(): Int = postList.size

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.binding.post = postList[position]

        holder.binding.root.setOnClickListener {
            onItemSelectedListener?.invoke(postList[position])
        }
    }
}
