package io.devdiagon.multisites.ui.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.devdiagon.multisites.data.models.Site
import io.devdiagon.multisites.data.SitesRepository
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: SitesRepository
) : ViewModel() {
    var state  by mutableStateOf(UiState())
        private set

    fun onUiReady() {
        viewModelScope.launch {
            state = UiState(loading = true)

            // The repository handles getting the sites
            repository.sites.collect {
                // Only change if it's data inside
                if (it.isNotEmpty()) {
                    state = UiState(
                        loading = false,
                        sites = it
                    )
                }
            }
        }
    }
    /*init {
        viewModelScope.launch {
            state = UiState(loading = true)

            // The repository handles getting the sites
            repository.sites.collect {
                // Only change if it's data inside
                if (it.isNotEmpty()) {
                    state = UiState(
                        loading = false,
                        sites = it
                    )
                }
            }
        }
    }*/

    data class UiState(
        val loading : Boolean = false,
        val sites : List<Site> = emptyList<Site>()
    )
}