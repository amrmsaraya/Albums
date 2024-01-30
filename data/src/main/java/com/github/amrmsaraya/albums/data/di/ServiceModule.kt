package com.github.amrmsaraya.albums.data.di

import com.github.amrmsaraya.albums.data.remote.DefaultService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class ServiceModule {
    @Singleton
    @Provides
    fun provideDefaultService(retrofit: Retrofit): DefaultService {
        return retrofit.create(DefaultService::class.java)
    }
}