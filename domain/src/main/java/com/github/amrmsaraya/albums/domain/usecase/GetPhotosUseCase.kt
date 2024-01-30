package com.github.amrmsaraya.albums.domain.usecase

import com.github.amrmsaraya.albums.domain.entity.photo.Photo
import com.github.amrmsaraya.albums.domain.repository.DefaultRepository
import javax.inject.Inject

class GetPhotosUseCase @Inject constructor(private val repository: DefaultRepository) {
    suspend operator fun invoke(id: Int): Result<List<Photo>> {
        return repository.getPhotos(id)
    }
}