package com.github.amrmsaraya.albums.data.di

import com.github.amrmsaraya.albums.data.remote.DefaultService
import com.github.amrmsaraya.albums.data.repositoryImpl.DefaultRepositoryImpl
import com.github.amrmsaraya.albums.domain.repository.DefaultRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {
    @Singleton
    @Provides
    fun provideDefaultRepository(service: DefaultService): DefaultRepository {
        return DefaultRepositoryImpl(service = service)
    }
}