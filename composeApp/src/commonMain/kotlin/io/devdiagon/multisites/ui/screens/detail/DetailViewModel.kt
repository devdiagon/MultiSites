package io.devdiagon.multisites.ui.screens.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.devdiagon.multisites.data.models.Site
import io.devdiagon.multisites.data.SitesRepository
import io.devdiagon.multisites.ui.screens.home.HomeViewModel.UiState
import kotlinx.coroutines.launch

class DetailViewModel(
    private val siteId: String,
    private val repository: SitesRepository
): ViewModel() {
    var state by mutableStateOf(UiState())
        private set

    init {
        viewModelScope.launch {
            state = UiState(loading = false)

            // The repository handles getting the site details
            repository.fetchRawSiteDetails(siteId).collect {
                // Only change if it's data inside
                if (it != null) {
                    state = UiState(
                        loading = false,
                        site = it
                    )
                }
            }
        }
    }
    data class UiState(
        val loading: Boolean = false,
        val site: Site? = null
    )
}