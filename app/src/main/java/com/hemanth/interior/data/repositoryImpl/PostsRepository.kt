package com.hemanth.interior.data.repositoryImpl

import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.hemanth.interior.common.Constants
import com.hemanth.interior.data.model.Category
import com.hemanth.interior.data.repository.PostsRepository
import com.hemanth.interior.util.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await

/**
 * Repository for the data of posts.
 * This will be a single source of data throughout the application.
 */
class PostsRepositoryImpl constructor(firestore: FirebaseFirestore) : PostsRepository {

    private val mCategoryCollection = firestore.collection(Constants.COLLECTION_CATEGORY)

    override fun getAllCategories() = flow<State<List<Category>>> {
        emit(State.loading())
        val snapshot = mCategoryCollection.get().await()
        val posts = snapshot.toObjects(Category::class.java)
        emit(State.success(posts))

    }

    override fun addCategory(category: Category) = flow<State<DocumentReference>> {
        emit(State.loading())
        val postRef = mCategoryCollection.add(category).await()
        emit(State.success(postRef))
    }
}
