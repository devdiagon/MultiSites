package io.devdiagon.multisites.ui.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.devdiagon.multisites.data.Features
import io.devdiagon.multisites.data.Site
import io.devdiagon.multisites.data.SitesRepository
import io.devdiagon.multisites.data.Sitexid
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: SitesRepository
) : ViewModel() {
    var state  by mutableStateOf(UiState())
        private set

    init {
        viewModelScope.launch {
            state = UiState(loading = true)
            val rawSites = repository.fetchRawSitesIds().features.map { it.toListxids() }

            state = UiState(
                loading = false,
                // Get popular remote Results by using its xid
                // Get the actual site using based on the xid
                sites = rawSites.map {
                    val rawSite = repository.fetchRawSiteDetails(it.xid)
                    Site(
                        id = rawSite.xid,
                        name = rawSite.name,
                        image = rawSite.preview.source,
                        description = rawSite.extracts.text
                    )
                }
            )
        }
    }

    data class UiState(
        val loading : Boolean = false,
        val sites : List<Site> = emptyList<Site>()
    )
}

private fun Features.toListxids() = Sitexid(
    xid = this.properties.xid
)