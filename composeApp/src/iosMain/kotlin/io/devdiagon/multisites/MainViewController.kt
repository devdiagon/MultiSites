package io.devdiagon.multisites

import androidx.compose.ui.window.ComposeUIViewController
import io.devdiagon.multisites.data.database.getDatabaseBuilder

fun MainViewController() = ComposeUIViewController {
    val database = getDatabaseBuilder().build()
    App(database.sitesDao())
}