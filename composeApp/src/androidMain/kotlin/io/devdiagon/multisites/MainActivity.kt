package io.devdiagon.multisites

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import io.devdiagon.multisites.data.database.getDatabaseBuilder

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val db = getDatabaseBuilder(this).build()
            App(db.sitesDao())
        }
    }
}