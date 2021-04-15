package com.hemanth.interior.data.repository

import com.google.firebase.firestore.DocumentReference
import com.hemanth.interior.data.model.Category
import com.hemanth.interior.data.model.Post
import com.hemanth.interior.util.State
import kotlinx.coroutines.flow.Flow

interface PostsRepository {

    /**
     * Returns Flow of [State] which retrieves all posts from cloud firestore collection.
     */

    fun getAllPosts(): Flow<State<List<Post>>>

    /**
     * Adds post [post] into the cloud firestore collection.
     * @return The Flow of [State] which will store state of current action.
     */
    fun addPost(post: Post): Flow<State<DocumentReference>>


    /**
     * Returns Flow of [State] which retrieves all category from cloud firestore collection.
     */

    fun getAllCategories(): Flow<State<List<Category>>>

    /**
     * Adds category [category] into the cloud firestore collection.
     * @return The Flow of [State] which will store state of current action.
     */
    fun addCategory(category: Category): Flow<State<DocumentReference>>
}
