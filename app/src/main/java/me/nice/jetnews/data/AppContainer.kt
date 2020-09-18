package me.nice.jetnews.data

import me.nice.jetnews.data.interest.InterestRepository
import me.nice.jetnews.data.posts.PostsRepository

interface AppContainer {
    val postsRepository: PostsRepository
    val interestRepository: InterestRepository
}