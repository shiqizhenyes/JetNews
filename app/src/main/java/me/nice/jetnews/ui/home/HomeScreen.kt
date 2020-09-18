package me.nice.jetnews.ui.home

import androidx.compose.foundation.Image
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import me.nice.jetnews.data.posts.PostsRepository
import me.nice.jetnews.model.Post
import me.nice.jetnews.ui.Screen
import me.nice.jetnews.ui.state.UiState
import me.nice.jetnews.util.launchUiStateProducer


@Composable
fun HomeScreen(
    navigateTo: (Screen) -> Unit,
    postsRepository: PostsRepository, scaffoldState: ScaffoldState = rememberScaffoldState()
) {
    val (postUiState, refreshPost, clearError) = launchUiStateProducer(postsRepository) {
        getPosts()
    }

//    val favorites by postsRepository.observeFavorites().collectAsState(setOf())

}

@Composable
fun HomeScreen(
    posts: UiState<List<Post>>,
    favorites: Set<String>,
    onToggleFavorite: (String) -> Unit,
    onRefreshPosts: () -> Unit,
    onErrorDismiss: () -> Unit,
    navigateTo: (Screen) -> Unit,
    scaffoldState: ScaffoldState
) {
    Scaffold(scaffoldState = scaffoldState,
    drawerContent = {

    }) {
//        Image(asset = )

    }
}