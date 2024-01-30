package com.github.amrmsaraya.albums.presentation.screen.photos

sealed interface PhotosIntent {
    data class GetPhotos(val albumId: Int) : PhotosIntent
    data object ResetError : PhotosIntent
}