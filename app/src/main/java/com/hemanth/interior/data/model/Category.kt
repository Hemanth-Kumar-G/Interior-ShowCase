package com.hemanth.interior.data.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class Category(
    val categoryTitle: String? = null,
    val categoryUrl: String? = null,
    val post: List<String>? = null
): Parcelable
