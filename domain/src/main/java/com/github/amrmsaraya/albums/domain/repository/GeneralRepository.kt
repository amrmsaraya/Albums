package com.github.amrmsaraya.albums.domain.repository

import com.github.amrmsaraya.albums.domain.entity.album.Album
import com.github.amrmsaraya.albums.domain.entity.photo.Photo
import com.github.amrmsaraya.albums.domain.entity.user.User

interface DefaultRepository {
    suspend fun getUsers(): Result<List<User>>
    suspend fun getAlbums(id: Int): Result<List<Album>>
    suspend fun getPhotos(id: Int): Result<List<Photo>>
}