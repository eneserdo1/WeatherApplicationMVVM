package com.app.weatherapplicationmvvm.di

import com.app.weatherapplicationmvvm.data.Repository
import com.app.weatherapplicationmvvm.data.remote.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideRepository(
        service: ApiService,
    ): Repository {
        return Repository(service)
    }
}