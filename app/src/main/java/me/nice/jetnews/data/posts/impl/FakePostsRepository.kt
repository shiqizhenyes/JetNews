package me.nice.jetnews.data.posts.impl

import android.content.res.Resources
import androidx.compose.ui.graphics.imageFromResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import me.nice.jetnews.data.Result
import me.nice.jetnews.data.posts.PostsRepository
import me.nice.jetnews.model.Post
import me.nice.jetnews.util.addOrRemove
import java.lang.IllegalArgumentException
import java.lang.IllegalStateException

@OptIn(ExperimentalCoroutinesApi::class)
class FakePostsRepository(private val resources: Resources): PostsRepository {

    private val postsWithResources: List<Post> by lazy {
        posts.map {
            it.copy(
                image = imageFromResource(resources, it.imageId),
                imageThumb = imageFromResource(resources, it.imageThumbId)
            )
        }
    }

    private val favorites = MutableStateFlow<Set<String>>(setOf())
    private val mutex = Mutex()

    override suspend fun getPost(postId: String): Result<Post> {
        return withContext(Dispatchers.IO) {
            val  post = postsWithResources.find { it.id == postId }
            if (post == null) {
                Result.Error(IllegalArgumentException("Post not found"))
            } else {
                Result.Success(post)
            }
        }
    }

    override suspend fun getPosts(): Result<List<Post>> {
        return withContext(Dispatchers.IO) {
            delay(800L)
            if (shouldRandomlyFail()) {
                Result.Error(IllegalStateException())
            } else {
                Result.Success(postsWithResources)
            }
        }
    }


    override fun observeFavorites(): Flow<Set<String>> = favorites

    override suspend fun toggleFavorite(postId: String) {
        mutex.withLock {
            val set = favorites.value.toMutableSet()
            set.addOrRemove(postId)
            favorites.value = set.toSet()
        }
    }

    private var requestCount = 0

    private fun shouldRandomlyFail(): Boolean = ++requestCount % 5 == 0

}