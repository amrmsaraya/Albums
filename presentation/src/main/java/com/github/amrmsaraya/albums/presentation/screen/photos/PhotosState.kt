package com.github.amrmsaraya.albums.presentation.screen.photos

import com.github.amrmsaraya.albums.domain.entity.photo.Photo

data class PhotosState(
    val photos: List<Photo> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)