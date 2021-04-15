package com.hemanth.interior.di

import com.google.firebase.firestore.FirebaseFirestore
import com.hemanth.interior.data.repository.PostsRepository
import com.hemanth.interior.data.repositoryImpl.PostsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideFireStoreInstance(): FirebaseFirestore = FirebaseFirestore.getInstance()

    @Singleton
    @Provides
    fun providePostsRepository(firestore: FirebaseFirestore): PostsRepository =
        PostsRepositoryImpl(firestore)
}
