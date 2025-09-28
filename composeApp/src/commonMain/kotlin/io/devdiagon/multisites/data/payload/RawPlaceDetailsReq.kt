package io.devdiagon.multisites.data.payload

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RawPlaceDetailsReq(
    @SerialName("xid"               ) var xid      : String   = "",
    @SerialName("name"              ) var name     : String   = "",
    @SerialName("kinds"             ) var kinds    : String   = "",
    @SerialName("address"           ) var address  : Address  = Address(),
    @SerialName("preview"           ) var preview  : Preview  = Preview(),
    @SerialName("wikipedia_extracts") var extracts : Extracts = Extracts()
)

@Serializable
data class Preview (

    @SerialName("source") var source : String = "",
    @SerialName("height") var height : Int?   = 0,
    @SerialName("width" ) var width  : Int?   = 0
)

@Serializable
data class Extracts (
    @SerialName("title" ) var title : String? = null,
    @SerialName("text"  ) var text  : String  = ""
)

@Serializable
data class Address (
    @SerialName("county" ) var city    : String = "",
    @SerialName("country") var country : String = ""
)