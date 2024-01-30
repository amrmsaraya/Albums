package com.github.amrmsaraya.albums.domain.usecase

import com.github.amrmsaraya.albums.domain.entity.user.User
import com.github.amrmsaraya.albums.domain.repository.DefaultRepository
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(private val repository: DefaultRepository) {
    suspend operator fun invoke(): Result<List<User>> {
        return repository.getUsers()
    }
}