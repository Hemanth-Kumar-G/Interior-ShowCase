package com.hemanth.interior.ui.home


import androidx.fragment.app.viewModels
import com.hemanth.interior.R
import com.hemanth.interior.BR
import com.hemanth.interior.base.BaseFragment
import com.hemanth.interior.databinding.HomeFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<HomeFragmentBinding, HomeViewModel>() {

    private val homeViewModel: HomeViewModel by viewModels()

    override fun getLayoutId(): Int = R.layout.home_fragment

    override fun getBindingVariable(): Int = BR._all

    override fun getViewModel(): HomeViewModel = homeViewModel

}
