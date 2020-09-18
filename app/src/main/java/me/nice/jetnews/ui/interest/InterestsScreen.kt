package me.nice.jetnews.ui.interest

import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import me.nice.jetnews.data.interest.InterestRepository
import me.nice.jetnews.ui.Screen


@Composable
fun InterestsScreen(
    navigateTo: (Screen) -> Unit,
    interestRepository: InterestRepository,
    scaffoldState: ScaffoldState = rememberScaffoldState()
) {

}