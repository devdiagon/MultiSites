package io.devdiagon.multisites.data

import io.devdiagon.multisites.data.database.SitesDao
import io.devdiagon.multisites.data.models.Site
import io.devdiagon.multisites.data.models.Sitexid
import io.devdiagon.multisites.data.payload.Features
import io.devdiagon.multisites.data.payload.RawPlaceDetailsReq
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

class SitesRepository(
    private val sitesService: SitesService,
    private val sitesDao: SitesDao,
    private val regionRepository: RegionRepository
) {
    // Get the sites from the database
    val sites: Flow<List<Site>> = sitesDao.fetchRelevantSites().onEach { sites ->
        // In case it was empty, save the SITE into the database
        if(sites.isEmpty()) {
            // Fetch the location latitude and longitude
            val location = regionRepository.fetchRegion()
            val regionLocation = sitesService.fetchLocationSpecs(location)

            // Fetch the site xids first by the region
            val rawSitesIds = sitesService.fetchRawSitesIds(regionLocation.lon, regionLocation.lat)
                .features.map { it.toListxids() }
            // Get the details for every site xid
            val fetchedSites = rawSitesIds.map { sitexid ->
                sitesService.getSiteDetails(sitexid.xid).toDomainSite()
            }
            // Save the sites using the SitesDao
            sitesDao.save(fetchedSites)
        }
    }

    suspend fun fetchRawSiteDetails(xid: String): Flow<Site?> = sitesDao.fetchSiteById(xid).onEach { site ->
        if (site == null) {
            val fetchedSite = sitesService.getSiteDetails(xid).toDomainSite()
            sitesDao.save(listOf(fetchedSite))
        }
    }

    suspend fun toggleFavorite(site: Site) {
        sitesDao.save(listOf(site.copy(isFavorite = !site.isFavorite)))
    }
}

// We get the Raw JSON data obj from the request so we need to
// parse it into the Site model of the business domain
private fun RawPlaceDetailsReq.toDomainSite() = Site(
    id = this.xid,
    name = this.name,
    image = this.preview.source,
    description = this.extracts.text,
    city = this.address.city,
    country = this.address.country,
    kinds = this.kinds,
    isFavorite = false
)

private fun Features.toListxids() = Sitexid(
    xid = this.properties.xid
)