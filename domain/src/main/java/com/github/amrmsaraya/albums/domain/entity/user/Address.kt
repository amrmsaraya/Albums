package com.github.amrmsaraya.albums.domain.entity.user

data class Address(
    val street: String,
    val suite: String,
    val city: String,
    val zipcode: String,
    val geo: Geo
)