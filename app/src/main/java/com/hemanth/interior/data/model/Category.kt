package com.hemanth.interior.data.model

data class Category(
    val categoryTitle: String? = null,
    val categoryUrl: String? = null,
    val post: List<Post>? = null
)
