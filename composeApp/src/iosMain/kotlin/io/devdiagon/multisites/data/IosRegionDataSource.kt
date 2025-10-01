package io.devdiagon.multisites.data

class IosRegionDataSource: RegionDataSource {
    override suspend fun fetchRegion(): String {
        return "EC"
    }
}