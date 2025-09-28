package io.devdiagon.multisites.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RawPlacesReq(
    @SerialName("type"     ) var type     : String?             = null,
    @SerialName("features" ) var features : ArrayList<Features> = arrayListOf()
)

@Serializable
data class Properties (

    @SerialName("xid"      ) var xid      : String = "",
    @SerialName("name"     ) var name     : String = "",
    @SerialName("kinds"    ) var kinds    : String? = null

)

@Serializable
data class Features(
    @SerialName("type"       ) var type       : String?     = null,
    @SerialName("id"         ) var id         : String?     = null,
    @SerialName("properties" ) var properties : Properties = Properties()
)