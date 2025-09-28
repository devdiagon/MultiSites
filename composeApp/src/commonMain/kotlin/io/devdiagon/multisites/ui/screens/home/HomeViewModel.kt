package io.devdiagon.multisites.ui.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.devdiagon.multisites.data.Site
import io.devdiagon.multisites.data.SitesRepository
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: SitesRepository
) : ViewModel() {
    var state  by mutableStateOf(UiState())
        private set

    init {
        viewModelScope.launch {
            state = UiState(loading = true)
            // Get just the xid from the request
            val rawSites = repository.fetchRawSitesIds()

            state = UiState(
                loading = false,
                // Get the actual site using based on the xid (ik double API call)
                sites = rawSites.map {
                    repository.fetchRawSiteDetails(it.xid)
                }
            )
        }
    }

    data class UiState(
        val loading : Boolean = false,
        val sites : List<Site> = emptyList<Site>()
    )
}