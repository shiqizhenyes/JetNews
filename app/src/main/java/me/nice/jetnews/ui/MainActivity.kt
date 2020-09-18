package me.nice.jetnews.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.viewinterop.viewModel
import androidx.ui.tooling.preview.Preview
import me.nice.jetnews.JetNews
import me.nice.jetnews.ui.theme.JetNewsTheme

class MainActivity : AppCompatActivity() {

    val navigationViewModel by viewModels<NavigationViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val appContainer = (application as JetNews).container
        setContent {
            JetNewsApp(appContainer = appContainer,
                navigationViewModel = navigationViewModel)
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()

    }


}
