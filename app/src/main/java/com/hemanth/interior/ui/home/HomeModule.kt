package com.hemanth.interior.ui.home

import com.hemanth.interior.data.model.Category
import com.hemanth.interior.ui.home.adapter.HomeCategoryAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.FragmentScoped

@Module
@InstallIn(FragmentComponent::class)
class HomeModule {

    @FragmentScoped
    @Provides
    fun provideArrayList() = ArrayList<Category>()

    @FragmentScoped
    @Provides
    fun provideAdapter(list: ArrayList<Category>) = HomeCategoryAdapter(list)

}
