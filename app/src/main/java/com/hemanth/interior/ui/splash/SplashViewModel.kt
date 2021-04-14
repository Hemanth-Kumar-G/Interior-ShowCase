package com.hemanth.interior.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.hemanth.interior.base.BaseViewModel
import com.hemanth.interior.common.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor() : BaseViewModel<Any>() {

    private val _time = MutableLiveData<Boolean>()
    val routeEvent: LiveData<Boolean> = _time

    fun initStart() {
        viewModelScope.launch {
            kotlinx.coroutines.delay(Constants.SPLASH_DELAY_MILLIS)
            postRouteEvent(true)
        }
    }

    private fun postRouteEvent(b: Boolean) {
        _time.postValue(b)
    }
}
