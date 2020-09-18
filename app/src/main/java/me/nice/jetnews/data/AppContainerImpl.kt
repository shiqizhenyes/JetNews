package me.nice.jetnews.data

import android.content.Context
import me.nice.jetnews.data.interest.FakeInterestRepository
import me.nice.jetnews.data.interest.InterestRepository
import me.nice.jetnews.data.posts.PostsRepository
import me.nice.jetnews.data.posts.impl.FakePostsRepository


class AppContainerImpl(private val applicationContext: Context) : AppContainer {
    override val postsRepository: PostsRepository by lazy {
        FakePostsRepository(
            resources = applicationContext.resources
        )
    }
    override val interestRepository: InterestRepository by lazy {
        FakeInterestRepository()
    }
}