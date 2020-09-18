package me.nice.jetnews.ui

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ListAlt
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.VectorAsset
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import me.nice.jetnews.R
import me.nice.jetnews.data.AppContainer
import me.nice.jetnews.data.interest.InterestRepository
import me.nice.jetnews.data.posts.PostsRepository
import me.nice.jetnews.data.posts.impl.FakePostsRepository
import me.nice.jetnews.ui.article.ArticleScreen
import me.nice.jetnews.ui.home.HomeScreen
import me.nice.jetnews.ui.interest.InterestsScreen
import me.nice.jetnews.ui.theme.JetNewsTheme


@Composable
fun JetNewsApp(appContainer: AppContainer, navigationViewModel: NavigationViewModel) {
    JetNewsTheme {
        AppContent(navigationViewModel = navigationViewModel,
            postsRepository = appContainer.postsRepository,
            interestRepository = appContainer.interestRepository)
    }
}

@Composable
private fun AppContent(navigationViewModel: NavigationViewModel,
                       postsRepository: PostsRepository,
                       interestRepository: InterestRepository) {
    Crossfade(navigationViewModel.currentScreen) {screen ->
        Surface(color = MaterialTheme.colors.background) {
            when(screen) {
                is Screen.Home -> HomeScreen(navigateTo = navigationViewModel::navigateTo,
                    postsRepository = postsRepository)
                is Screen.Interests -> InterestsScreen(navigateTo = navigationViewModel::navigateTo,
                    interestRepository = interestRepository)
                is Screen.Article -> ArticleScreen(postId = screen.postId,
                    postsRepository = postsRepository,
                    obBack = {navigationViewModel.onBack()})
            }
        }

    }

}

@Composable
fun AppDrawer(navigateTo: (Screen) -> Unit,
              currentScreen: Screen,
              closeDrawer:() -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.padding(16.dp))
        JetNewsLogo(modifier = Modifier.padding(16.dp))
        Divider(color = MaterialTheme.colors.onSurface.copy(alpha = .2f))
        DrawerButton(icon = Icons.Filled.Home,
                label = "Home",
                isSelected = currentScreen == Screen.Home,
                action = {
                    navigateTo(Screen.Home)
                    closeDrawer
                })
        DrawerButton(icon = Icons.Filled.ListAlt,
                label = "Interests",
                isSelected = currentScreen == Screen.Interests,
                action = {
                    navigateTo(Screen.Interests)
                    closeDrawer
                })
    }
}


@Composable
private fun JetNewsLogo(modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        Image(
            asset = vectorResource(id = R.drawable.ic_jetnews_logo),
            colorFilter = ColorFilter.tint(MaterialTheme.colors.primary)
        )
        Spacer(Modifier.preferredHeight(8.dp))
        Image(
            asset = vectorResource(id = R.drawable.ic_jetnews_wordmark),
            colorFilter = ColorFilter.tint(MaterialTheme.colors.onSurface)
        )
    }
}

@Composable
private fun DrawerButton(
    icon: VectorAsset,
    label: String,
    isSelected: Boolean,
    action: () -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = MaterialTheme.colors
    val imageAlpha = if(isSelected) {
        1f
    } else {
        0.6f
    }
    val textIconColor = if (isSelected) {
        colors.primary
    } else {
        colors.onSurface.copy(alpha = 0.6f)
    }

    val backgroundColor = if (isSelected) {
        colors.primary.copy(alpha = 0.12f)
    }else {
        Color.Transparent
    }

    val surfaceModifier = modifier.padding(start = 8.dp, top = 8.dp, end = 8.dp)
            .fillMaxWidth()
    Surface(modifier = surfaceModifier,
            color = backgroundColor,
            shape = MaterialTheme.shapes.small) {
        TextButton(onClick = action, modifier = Modifier.fillMaxWidth()) {
            Row(horizontalArrangement = Arrangement.Start,
            verticalGravity = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()) {
                Image(
                        asset = icon,
                        colorFilter = ColorFilter.tint(textIconColor),
                        alpha = imageAlpha
                )
                Spacer(modifier = Modifier.preferredWidth(16.dp))
                Text(text = label, style = MaterialTheme.typography.body2,
                color = textIconColor, modifier = Modifier.fillMaxWidth())
            }
        }
    }
}



//@Preview("Drawer contents")
//@Composable
//fun PreviewJetNewsApp() {
//    ThemedPreview(darkTheme = false) {
//        AppDrawer(navigateTo = {},
//                currentScreen = Screen.Home,
//                closeDrawer = {})
//    }
//}



@Preview("Drawer contents")
@Composable
fun PreviewJetNewsDarkApp() {
    ThemedPreview(darkTheme = true) {
        AppDrawer(navigateTo = {},
                currentScreen = Screen.Home,
                closeDrawer = {})
    }
}














