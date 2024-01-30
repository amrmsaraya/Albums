package com.github.amrmsaraya.albums.domain.usecase

import com.github.amrmsaraya.albums.domain.entity.album.Album
import com.github.amrmsaraya.albums.domain.repository.DefaultRepository
import javax.inject.Inject

class GetAlbumsUseCase @Inject constructor(private val repository: DefaultRepository) {
    suspend operator fun invoke(id: Int): Result<List<Album>> {
        return repository.getAlbums(id)
    }
}