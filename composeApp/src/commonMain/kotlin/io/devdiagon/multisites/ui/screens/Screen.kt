package io.devdiagon.multisites.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.ktor.http.content.OutgoingContent

@Composable
fun Screen(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    MaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            content = content
        )
    }
}