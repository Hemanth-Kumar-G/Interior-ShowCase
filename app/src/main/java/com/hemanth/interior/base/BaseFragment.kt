package com.hemanth.interior.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

abstract class BaseFragment<T : ViewDataBinding, V : BaseViewModel<*>> : Fragment() {

    private var viewDataBinding: T? = null

    private var mFragmentView: View? = null

    private var snackbar: Snackbar? = null


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


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        mFragmentView = viewDataBinding?.root
        return mFragmentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        performDataBinding()
    }

    /**
     * Inflate the layout and initialize the databinding variable and
     * attach it to the corresponding viewmodel
     */
    private fun performDataBinding() {
        getViewModel().let {
            viewDataBinding?.apply {
                setVariable(getBindingVariable(), it)
                lifecycleOwner = this@BaseFragment
                executePendingBindings()
            }
        }
    }

    /**
     * Show toast message
     */
    fun showToast(messageResId: Int) {
        context?.let { context ->
            Toast.makeText(context, messageResId, Toast.LENGTH_SHORT).show()
        }
    }


    /**
     * Show toast message
     */
    fun showToast(message: String?) {
        if (!message.isNullOrEmpty()) {
            context?.let { context ->
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            }
        }
    }


    fun showSnackbar(message: String, duration: Int = Snackbar.LENGTH_SHORT) {
        view?.let {
            showSnackbar(message, duration, null, null)
        }
    }

    fun showSnackbar(message: String, duration: Int = Snackbar.LENGTH_SHORT, action: String? = null, listener: View.OnClickListener? = null) {
        view?.let {
            snackbar = Snackbar.make(it, message, duration)

            action?.let {
                snackbar?.setAction(action, listener)
            }
            snackbar?.show()
        }
    }

    fun hideSnackBar() = snackbar?.dismiss()

}
