package com.github.amrmsaraya.albums.data.repositoryImpl

import com.github.amrmsaraya.albums.common.di.IoDispatcher
import com.github.amrmsaraya.albums.data.remote.DefaultService
import com.github.amrmsaraya.albums.domain.entity.album.Album
import com.github.amrmsaraya.albums.domain.entity.photo.Photo
import com.github.amrmsaraya.albums.domain.entity.user.User
import com.github.amrmsaraya.albums.domain.repository.DefaultRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DefaultRepositoryImpl @Inject constructor(
    private val service: DefaultService,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : DefaultRepository {
    override suspend fun getUsers(): Result<List<User>> {
        return withContext(dispatcher) {
            service.getUsers().map { users ->
                users.map { it.toDomain() }
            }
        }
    }

    override suspend fun getAlbums(id: Int): Result<List<Album>> {
        return withContext(dispatcher) {
            service.getAlbums(id).map { albums ->
                albums.map { it.toDomain() }
            }
        }
    }

    override suspend fun getPhotos(id: Int): Result<List<Photo>> {
        return withContext(dispatcher) {
            service.getPhotos(id).map { photos ->
                photos.map { it.toDomain() }
            }
        }
    }
}