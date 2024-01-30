package com.github.amrmsaraya.albums.data.model.user


import com.github.amrmsaraya.albums.domain.entity.user.Geo
import com.squareup.moshi.Json

data class GeoDTO(
    @Json(name = "lat")
    val lat: String?,
    @Json(name = "lng")
    val lng: String?
) {
    fun toDomain(): Geo {
        return Geo(
            lat = lat ?: "",
            lng = lng ?: ""
        )
    }
}