package com.hemanth.interior.ui.post

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.hemanth.interior.R
import com.hemanth.interior.BR
import com.hemanth.interior.base.BaseFragment
import com.hemanth.interior.common.Constants
import com.hemanth.interior.data.model.Category
import com.hemanth.interior.databinding.PostFragmentBinding
import com.hemanth.interior.ui.post.adapter.PostAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PostFragment : BaseFragment<PostFragmentBinding, PostViewModel>() {

    @Inject
    lateinit var postAdapter: PostAdapter

    @Inject
    lateinit var postList: ArrayList<String>

    private val postViewModel: PostViewModel by viewModels()

    override fun getLayoutId(): Int = R.layout.post_fragment

    override fun getBindingVariable(): Int = BR.postViewModel

    override fun getViewModel(): PostViewModel = postViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getParcelable<Category>(Constants.CATEGORY)?.let { category ->
            category.post?.let {
                postList.addAll(it)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()

    }

    private fun init() {
        dataBinding?.rvPostList?.adapter = postAdapter
        postAdapter.notifyDataSetChanged()

        dataBinding?.ivPostBack?.setOnClickListener { activity?.onBackPressed() }
    }


}
