package io.devdiagon.multisites

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import coil3.ImageLoader
import coil3.compose.setSingletonImageLoaderFactory
import coil3.request.crossfade
import coil3.util.DebugLogger
import io.devdiagon.multisites.ui.screens.home.HomeScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun App() {
    // Define builder image behaviour by default
    setSingletonImageLoaderFactory { context ->
        ImageLoader.Builder(context)
            .crossfade(true)
            .logger(DebugLogger())
            .build()
    }
    HomeScreen()
}