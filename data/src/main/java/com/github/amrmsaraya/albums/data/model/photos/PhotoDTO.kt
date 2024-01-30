package com.github.amrmsaraya.albums.data.model.photos


import com.github.amrmsaraya.albums.domain.entity.photo.Photo
import com.squareup.moshi.Json

data class PhotoDTO(
    @Json(name = "albumId")
    val albumId: Int?,
    @Json(name = "id")
    val id: Int?,
    @Json(name = "title")
    val title: String?,
    @Json(name = "url")
    val url: String?,
    @Json(name = "thumbnailUrl")
    val thumbnailUrl: String?
) {
    fun toDomain(): Photo {
        return Photo(
            albumId = albumId ?: 0,
            id = id ?: 0,
            title = title ?: "",
            url = url ?: "",
            thumbnailUrl = thumbnailUrl ?: ""
        )
    }
}