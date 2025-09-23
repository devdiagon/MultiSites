package io.devdiagon.multisites.data

data class Site(
    val id: Int,
    val name: String,
    val image: String,
    val description: String
)

val sites = (1..100).map {
    Site(
        id = it,
        name = "Site $it",
        image = "https://picsum.photos/200/300?id=$it",
        description = "Description not that long to catch the attention of the user $it"
    )
}
