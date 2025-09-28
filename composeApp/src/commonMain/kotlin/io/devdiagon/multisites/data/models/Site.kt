package io.devdiagon.multisites.data.models

data class Site(
    val id: String,
    val name: String,
    val image: String,
    val description: String,
    val city: String,
    val country: String,
    val kinds: String
)