package com.hemanth.interior.ui.home


import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.hemanth.interior.R
import com.hemanth.interior.BR
import com.hemanth.interior.base.BaseFragment
import com.hemanth.interior.data.model.Category
import com.hemanth.interior.data.model.Post
import com.hemanth.interior.databinding.HomeFragmentBinding
import com.hemanth.interior.ui.home.adapter.HomeCategoryAdapter
import com.hemanth.interior.util.NetworkConnectionUtil
import com.hemanth.interior.util.State
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "HomeFragment"

@AndroidEntryPoint
class HomeFragment : BaseFragment<HomeFragmentBinding, HomeViewModel>() {

    @Inject
    lateinit var adapter: HomeCategoryAdapter

    @Inject
    lateinit var categoryList: ArrayList<Category>

    @Inject
    lateinit var networkConnectionUtil: NetworkConnectionUtil

    private val homeViewModel: HomeViewModel by viewModels()

    override fun getLayoutId(): Int = R.layout.home_fragment

    override fun getBindingVariable(): Int = BR._all

    override fun getViewModel(): HomeViewModel = homeViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        if (networkConnectionUtil.isConnected()) {
            loadCategories()
        } else {
            networkConnectionUtil.showInternetError({
                init()
            }, requireContext())
        }
        dataBinding?.rvHomeCategoryList?.adapter = adapter
    }

    private fun loadCategories() {
        lifecycleScope.launch {
            homeViewModel.getAllCategories().collect { state ->
                when (state) {
                    is State.Loading -> {
                        homeViewModel.loading.set(true)
                    }

                    is State.Success -> {
                        homeViewModel.loading.set(false)
                        categoryList.clear()
                        categoryList.addAll(state.data)
                        adapter.notifyDataSetChanged()
                    }

                    is State.Failed -> {
                        showToast("Failed! ${state.message}")
                        homeViewModel.loading.set(false)
                    }
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
