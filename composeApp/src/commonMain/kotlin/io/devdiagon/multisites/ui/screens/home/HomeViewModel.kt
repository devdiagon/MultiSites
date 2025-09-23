package io.devdiagon.multisites.ui.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.devdiagon.multisites.Site
import io.devdiagon.multisites.sites
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    // Artificial loading state
    var state  by mutableStateOf(UiState())
        private set

    init {
        viewModelScope.launch {
            state = UiState(loading = true)
            delay(1000)

            state = UiState(loading = false, sites = sites)
        }
    }

    data class UiState(
        val loading : Boolean = false,
        val sites : List<Site> = emptyList<Site>()
    )
}