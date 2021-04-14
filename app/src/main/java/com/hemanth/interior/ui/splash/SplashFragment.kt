package com.hemanth.interior.ui.splash

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.hemanth.interior.R
import com.hemanth.interior.BR
import com.hemanth.interior.base.BaseFragment
import com.hemanth.interior.databinding.SplashFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : BaseFragment<SplashFragmentBinding, SplashViewModel>() {

    private val splashViewModel: SplashViewModel by viewModels()

    override fun getLayoutId(): Int = R.layout.splash_fragment

    override fun getBindingVariable(): Int = BR.splashViewModel

    override fun getViewModel(): SplashViewModel = splashViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        splashViewModel.initStart()
        setupObserver()
    }

    private fun setupObserver() {
        splashViewModel.routeEvent.observe(requireActivity(), Observer {
            if (it) {
                launchHomeScreen()
            }
        })
    }

    private fun launchHomeScreen() {
        findNavController().navigate(R.id.splash_action)
    }
}
