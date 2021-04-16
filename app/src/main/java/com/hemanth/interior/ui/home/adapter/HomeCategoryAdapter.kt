package com.hemanth.interior.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hemanth.interior.data.model.Category
import com.hemanth.interior.databinding.CategoryItemBinding


class HomeCategoryAdapter(private val categoryList: ArrayList<Category>) :
    RecyclerView.Adapter<HomeCategoryAdapter.HomeNewsListViewHolder>() {

    var onItemSelectedListener: ((category: Category) -> Unit)? = null

    class HomeNewsListViewHolder(val binding: CategoryItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeNewsListViewHolder {
        val binding =
            CategoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeNewsListViewHolder(binding)
    }

    override fun getItemCount(): Int = categoryList.size

    override fun onBindViewHolder(holder: HomeNewsListViewHolder, position: Int) {
        holder.binding.category = categoryList[position]

        holder.binding.root.setOnClickListener {
            onItemSelectedListener?.invoke(categoryList[position])
        }
    }
}
