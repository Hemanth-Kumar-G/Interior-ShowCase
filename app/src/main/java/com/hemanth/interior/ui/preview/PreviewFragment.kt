package com.hemanth.interior.ui.preview

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.hemanth.interior.BR
import com.hemanth.interior.R
import com.hemanth.interior.base.BaseFragment
import com.hemanth.interior.databinding.PreviewFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PreviewFragment : BaseFragment<PreviewFragmentBinding, PreviewViewModel>() {

    private val previewViewModel: PreviewViewModel by viewModels()

    override fun getLayoutId(): Int = R.layout.preview_fragment

    override fun getBindingVariable(): Int = BR._all

    override fun getViewModel(): PreviewViewModel = previewViewModel

    var postUrl: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        postUrl = arguments?.getString("POST")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        postUrl?.let { dataBinding?.imageDraweeView?.setImageURI(Uri.parse(it)) }
//        postUrl?.let { dataBinding?.imageDraweeView?.setPhotoUri(Uri.parse(it)) }
//        dataBinding?.imageDraweeView?im.setOnDoubleTapListener(null);
        dataBinding?.ivPostBack?.setOnClickListener { findNavController().navigateUp() }
    }
}
