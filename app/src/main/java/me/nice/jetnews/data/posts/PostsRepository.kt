package me.nice.jetnews.data.posts

import kotlinx.coroutines.flow.Flow
import me.nice.jetnews.model.Post
import me.nice.jetnews.data.Result

interface PostsRepository {

    suspend fun getPost(postId: String): Result<Post>

    suspend fun getPosts(): Result<List<Post>>

    fun observeFavorites(): Flow<Set<String>>

    suspend fun toggleFavorite(postId: String)

}