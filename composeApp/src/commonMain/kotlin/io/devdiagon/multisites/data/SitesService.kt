package io.devdiagon.multisites.data

import io.devdiagon.multisites.data.payload.RawPlaceDetailsReq
import io.devdiagon.multisites.data.payload.RawPlacesReq
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class SitesService(private val client : HttpClient) {
    suspend fun fetchRawSitesIds(): RawPlacesReq {
        return client
            .get("/0.1/en/places/radius") {
                // HARDCODED parameters
                parameter("radius","10000")
                parameter("lon","-78.4678")
                parameter("lat","-0.1807")
                parameter("limit","15")
                parameter("kinds","museums,historic,foods,architecture,natural")
                parameter("rate", "3")
            }
            //.get("/0.1/en/places/radius?radius=10000&lon=-78.4678&lat=-0.1807&limit=15&kinds=museums,historic,foods,architecture,natural&rate=3")
            .body<RawPlacesReq>()  //Serialize the result
    }

    suspend fun getSiteDetails(xid: String): RawPlaceDetailsReq {
        return client
            .get("/0.1/en/places/xid/$xid?")
            .body<RawPlaceDetailsReq>()
    }
}