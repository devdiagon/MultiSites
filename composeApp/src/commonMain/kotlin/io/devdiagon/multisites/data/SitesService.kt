package io.devdiagon.multisites.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class SitesService(
    private val apiKey : String,
    private val client : HttpClient
) {
    suspend fun fetchRawSitesIds(): RawPlacesReq {
        return client
            .get("https://api.opentripmap.com/0.1/en/places/radius?radius=10000&lon=-78.4678&lat=-0.1807&limit=50&kinds=museums,historic,foods,architecture,natural&rate=3&apikey=$apiKey")
            .body<RawPlacesReq>()  //Serialize the result
    }

    suspend fun getSiteDetails(xid: String): RawPlaceDetailsReq {
        return client
            .get("https://api.opentripmap.com/0.1/en/places/xid/$xid?apikey=$apiKey")
            .body<RawPlaceDetailsReq>()
    }
}