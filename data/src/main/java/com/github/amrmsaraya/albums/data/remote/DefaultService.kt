package com.github.amrmsaraya.albums.data.remote

import com.github.amrmsaraya.albums.data.model.album.AlbumDTO
import com.github.amrmsaraya.albums.data.model.photos.PhotoDTO
import com.github.amrmsaraya.albums.data.model.user.UserDTO
import com.github.amrmsaraya.albums.network.EndPoint
import retrofit2.http.GET
import retrofit2.http.Path

interface DefaultService {
    @GET(EndPoint.GET_USERS)
    suspend fun getUsers(): Result<List<UserDTO>>

    @GET(EndPoint.GET_ALBUMS)
    suspend fun getAlbums(@Path("id") id: Int): Result<List<AlbumDTO>>

    @GET(EndPoint.GET_PHOTOS)
    suspend fun getPhotos(@Path("id") id: Int): Result<List<PhotoDTO>>
}