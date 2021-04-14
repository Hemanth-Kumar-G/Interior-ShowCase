package com.hemanth.interior.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.hemanth.interior.R
import com.hemanth.interior.BR
import com.hemanth.interior.base.BaseActivity
import com.hemanth.interior.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    private lateinit var navController: NavController

    private val mainViewModel: MainViewModel by viewModels()

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun getBindingVariable(): Int = BR.mainViewModel

    override fun getViewModel(): MainViewModel = mainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init() {
        navController = (supportFragmentManager.findFragmentById(R.id.navHostMain) as NavHostFragment).navController
    }

}
