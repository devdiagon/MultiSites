package io.devdiagon.multisites.data

class AndroidRegionDataSource: RegionDataSource {
    override suspend fun fetchRegion(): String {
        return "EC"
    }
}