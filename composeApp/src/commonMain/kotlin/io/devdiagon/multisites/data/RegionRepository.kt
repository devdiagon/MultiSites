package io.devdiagon.multisites.data

interface RegionDataSource {
    suspend fun fetchRegion(): String
}

const val DEFAULT_REGION = "EC"

class RegionRepository(
    private val regionDataSource: RegionDataSource
) {
    suspend fun fetchRegion(): String {
        return regionDataSource.fetchRegion()
    }
}