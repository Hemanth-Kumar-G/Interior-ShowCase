package com.hemanth.interior.di

import android.content.Context
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore
import com.hemanth.interior.data.repository.PostsRepository
import com.hemanth.interior.data.repositoryImpl.PostsRepositoryImpl
import com.hemanth.interior.util.NetworkConnectionUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideFireStoreInstance(@ApplicationContext context: Context): FirebaseFirestore {
        FirebaseApp.initializeApp(context)
        return FirebaseFirestore.getInstance()
    }

    @Singleton
    @Provides
    fun providePostsRepository(firestore: FirebaseFirestore): PostsRepository =
        PostsRepositoryImpl(firestore)

    @Singleton
    @Provides
    fun provideNetworkConnectionUtil(@ApplicationContext context: Context) =
        NetworkConnectionUtil(context)
}
