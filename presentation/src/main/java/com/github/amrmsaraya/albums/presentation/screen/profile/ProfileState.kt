package com.github.amrmsaraya.albums.presentation.screen.profile

import com.github.amrmsaraya.albums.domain.entity.album.Album
import com.github.amrmsaraya.albums.domain.entity.user.User

data class ProfileState(
    val user: User? = null,
    val albums: List<Album> = emptyList(),
    val isUsersLoading: Boolean = false,
    val isAlbumsLoading: Boolean = false,
    val error: String? = null
)