package com.hemanth.interior.common

import android.app.Activity
import android.view.View
import androidx.databinding.BindingAdapter
import com.facebook.drawee.view.SimpleDraweeView


object AppBindingAdapters {

    @BindingAdapter("android:imageUri")
    @JvmStatic
    fun setImageUri(view: SimpleDraweeView, imageUri: String?) {
        imageUri?.let {
            view.setImageURI(imageUri)
        } ?: view.setImageURI("")
    }


    @BindingAdapter("onBackClick")
    @JvmStatic
    fun setOnBackPress(view: View, value: Boolean) {
        view.setOnClickListener {
            val activity: Activity = view.context as Activity
            activity.onBackPressed()
        }
    }

}
