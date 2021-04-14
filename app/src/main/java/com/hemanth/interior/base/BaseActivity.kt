package com.hemanth.interior.base

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<T : ViewDataBinding, V : BaseViewModel<*>> : AppCompatActivity() {

    var viewDataBinding: T? = null

    /**
     *  Override for set layout resource id
     */
    @LayoutRes
    abstract fun getLayoutId(): Int


    /**
     * Override for set binding variable
     */
    abstract fun getBindingVariable(): Int


    /**
     * Override for set view model
     */
    abstract fun getViewModel(): V

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performDataBinding()
    }

    /**
     * Inflate the layout and initialize the databinding variable and
     * attach it to the corresponding viewmodel
     */
    private fun performDataBinding() {
        viewDataBinding = DataBindingUtil.setContentView(this, getLayoutId())
        getViewModel().let {
            viewDataBinding?.setVariable(getBindingVariable(), it)
            viewDataBinding?.lifecycleOwner = this
            viewDataBinding?.executePendingBindings()
        }
    }

    /**
     * Show toast message
     */
    fun showToast(messageResId: Int) {
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()
    }

    /**
     * Show toast message
     */
    fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}
