package io.devdiagon.multisites.data

class SitesRepository(private val sitesService: SitesService) {
    suspend fun fetchRawSitesIds(): List<Sitexid> {
        return sitesService.fetchRawSitesIds().features.map { it.toListxids() }
    }

    suspend fun fetchRawSiteDetails(xid: String): RawPlaceDetailsReq {
        return sitesService.getSiteDetails(xid)
    }
}

private fun Features.toListxids() = Sitexid(
    xid = this.properties.xid
)