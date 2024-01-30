package com.github.amrmsaraya.albums.data.model.user


import com.github.amrmsaraya.albums.domain.entity.user.Address
import com.squareup.moshi.Json

data class AddressDTO(
    @Json(name = "street")
    val street: String?,
    @Json(name = "suite")
    val suite: String?,
    @Json(name = "city")
    val city: String?,
    @Json(name = "zipcode")
    val zipcode: String?,
    @Json(name = "geo")
    val geo: GeoDTO?
) {
    fun toDomain(): Address {
        return Address(
            street = street ?: "",
            suite = suite ?: "",
            city = city ?: "",
            zipcode = zipcode ?: "",
            geo = geo?.toDomain()
        )
    }
}