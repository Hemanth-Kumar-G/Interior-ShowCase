package com.hemanth.interior.ui.home

import androidx.databinding.ObservableBoolean
import com.hemanth.interior.base.BaseViewModel
import com.hemanth.interior.data.repository.PostsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: PostsRepository) :
    BaseViewModel<Any>() {

    val loading = ObservableBoolean(true)

    fun getAllCategories() = repository.getAllCategories()

}
