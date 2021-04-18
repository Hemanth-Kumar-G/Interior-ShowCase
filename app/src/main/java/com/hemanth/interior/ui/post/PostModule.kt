package com.hemanth.interior.ui.post

import com.hemanth.interior.ui.post.adapter.PostAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.FragmentScoped

@Module
@InstallIn(FragmentComponent::class)
class PostModule {

    @FragmentScoped
    @Provides
    fun provideArrayList() = ArrayList<String>()

    @FragmentScoped
    @Provides
    fun provideAdapter(list: ArrayList<String>) = PostAdapter(list)

}
