package io.devdiagon.multisites.data

class SitesRepository(private val sitesService: SitesService) {
    suspend fun fetchRawSitesIds(): RawPlacesReq {
        return sitesService.fetchRawSitesIds()
    }

    suspend fun fetchRawSiteDetails(xid: String): RawPlaceDetailsReq {
        return sitesService.getSiteDetails(xid)
    }
}