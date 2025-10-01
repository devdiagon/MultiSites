package io.devdiagon.multisites

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import coil3.ImageLoader
import coil3.compose.setSingletonImageLoaderFactory
import coil3.request.crossfade
import coil3.util.DebugLogger
import io.devdiagon.multisites.data.database.SitesDao
import io.devdiagon.multisites.ui.screens.Navigation
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun App(sitesDao: SitesDao) {
    // Define builder image behaviour by default
    setSingletonImageLoaderFactory { context ->
        ImageLoader.Builder(context)
            .crossfade(true)
            .logger(DebugLogger())
            .build()
    }
    Navigation(sitesDao)
}