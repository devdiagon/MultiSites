package io.devdiagon.multisites.data

import io.devdiagon.multisites.data.models.Site
import io.devdiagon.multisites.data.models.Sitexid
import io.devdiagon.multisites.data.payload.Features

class SitesRepository(private val sitesService: SitesService) {
    suspend fun fetchRawSitesIds(): List<Sitexid> {
        return sitesService.fetchRawSitesIds().features.map { it.toListxids() }
    }

    suspend fun fetchRawSiteDetails(xid: String): Site {
        val rawSite = sitesService.getSiteDetails(xid)
        return Site(
            id = rawSite.xid,
            name = rawSite.name,
            image = rawSite.preview.source,
            description = rawSite.extracts.text,
            city = rawSite.address.city,
            country = rawSite.address.country,
            kinds = rawSite.kinds
        )
    }
}

private fun Features.toListxids() = Sitexid(
    xid = this.properties.xid
)