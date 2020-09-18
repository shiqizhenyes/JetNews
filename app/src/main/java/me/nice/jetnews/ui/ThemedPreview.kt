package me.nice.jetnews.ui

import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import me.nice.jetnews.ui.theme.JetNewsTheme

@Composable
fun ThemedPreview(darkTheme: Boolean, children: @Composable () -> Unit) {
    JetNewsTheme(darkTheme = darkTheme) {
        Surface {
            children()
        }
    }
}