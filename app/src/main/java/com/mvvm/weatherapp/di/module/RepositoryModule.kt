package com.mvvm.weatherapp.di.module

import androidx.lifecycle.ViewModelProvider
import com.mvvm.weatherapp.ViewModelProviderFactory
import com.mvvm.weatherapp.data.repository.data_source.local.LocalDataSource
import com.mvvm.weatherapp.data.repository.data_source.remote.RemoteDataSource
import com.mvvm.weatherapp.data.repository.Repository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideRepository(
        localDataSource: LocalDataSource,
        remoteDataSource: RemoteDataSource
    ): Repository {
        return Repository(localDataSource, remoteDataSource)
    }

    @Singleton
    @Provides
    fun provideViewModelFactory(repository: Repository): ViewModelProvider.Factory {
        return ViewModelProviderFactory(repository)
    }

}