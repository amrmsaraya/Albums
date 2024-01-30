package com.github.amrmsaraya.albums.data.model.album


import com.github.amrmsaraya.albums.domain.entity.album.Album
import com.squareup.moshi.Json

data class AlbumDTO(
    @Json(name = "userId")
    val userId: Int?,
    @Json(name = "id")
    val id: Int?,
    @Json(name = "title")
    val title: String?
) {
    fun toDomain(): Album {
        return Album(
            userId = userId ?: 0,
            id = id ?: 0,
            title = title ?: ""
        )
    }
}