package com.github.amrmsaraya.albums.presentation.screen.profile

sealed interface ProfileIntent {
    data object GetUsers : ProfileIntent
    data class GetAlbums(val id: Int) : ProfileIntent
    data object ResetError : ProfileIntent
}