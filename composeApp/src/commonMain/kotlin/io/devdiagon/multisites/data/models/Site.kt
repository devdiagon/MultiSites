package io.devdiagon.multisites.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Site(
    @PrimaryKey
    val id: String,
    val name: String,
    val image: String,
    val description: String,
    val city: String,
    val country: String,
    val kinds: String
)