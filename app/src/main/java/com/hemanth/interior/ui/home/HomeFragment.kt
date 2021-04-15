package com.hemanth.interior.ui.home


import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.hemanth.interior.R
import com.hemanth.interior.BR
import com.hemanth.interior.base.BaseFragment
import com.hemanth.interior.data.model.Post
import com.hemanth.interior.databinding.HomeFragmentBinding
import com.hemanth.interior.util.State
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

private const val TAG = "HomeFragment"

@AndroidEntryPoint
class HomeFragment : BaseFragment<HomeFragmentBinding, HomeViewModel>() {

    private val homeViewModel: HomeViewModel by viewModels()

    override fun getLayoutId(): Int = R.layout.home_fragment

    override fun getBindingVariable(): Int = BR._all

    override fun getViewModel(): HomeViewModel = homeViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadPosts()
    }

    private fun loadPosts() {
        lifecycleScope.launch {
            homeViewModel.getAllPosts().collect { state ->
                when (state) {
                    is State.Loading -> {
                        showToast("Loading")
                    }

                    is State.Success -> {
                        val postText = state.data.joinToString("\n") {
                            "${it.postTitle} ~ ${it.postTitle}"
                        }
                        Log.e(TAG, "loadPosts: $postText")
                    }

                    is State.Failed -> showToast("Failed! ${state.message}")
                }
            }
        }
    }

    private suspend fun addPost(post: Post) {
        homeViewModel.addPost(post).collect { state ->
            when (state) {
                is State.Loading -> {
                    showToast("Loading")
                }

                is State.Success -> {
                    showToast("Posted")
                }

                is State.Failed -> {
                    showToast("Failed! ${state.message}")
                }
            }
        }
    }

}
