package com.github.amrmsaraya.albums.domain.entity.photo

data class Photo(
    val albumId: Int,
    val id: Int,
    val title: String,
    val url: String,
    val thumbnailUrl: String
)