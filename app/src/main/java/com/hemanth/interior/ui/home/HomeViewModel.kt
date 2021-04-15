package com.hemanth.interior.ui.home

import com.hemanth.interior.base.BaseViewModel
import com.hemanth.interior.data.model.Post
import com.hemanth.interior.data.repository.PostsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: PostsRepository) :
    BaseViewModel<Any>() {

    fun getAllPosts() = repository.getAllPosts()

    fun getAllCategories() = repository.getAllCategories()

    fun addPost(post: Post) = repository.addPost(post)

}
