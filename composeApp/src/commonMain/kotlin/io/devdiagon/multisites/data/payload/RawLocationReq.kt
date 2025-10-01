package io.devdiagon.multisites.data.payload

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RawLocationReq(
    @SerialName("name"   ) var name    : String = "",
    @SerialName("country") var country : String = "",
    @SerialName("lat"    ) var lat     : Double = 0.0,
    @SerialName("lon"    ) var lon     : Double = 0.0
)