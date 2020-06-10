package com.mvvm.weatherapp.di.module

import android.app.Application
import androidx.room.Room
import com.mvvm.weatherapp.data.local.AppDao
import com.mvvm.weatherapp.data.local.AppDatabase
import com.mvvm.weatherapp.data.repository.data_source.local.LocalDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DBModule constructor(private val application: Application) {

    private var appDatabase: AppDatabase

    init {
        appDatabase = Room.databaseBuilder(
            application.applicationContext,
            AppDatabase::class.java,
            "app.db"
        ).build()
    }

    @Singleton
    @Provides
    fun providesRoomDatabase(): AppDatabase? {
        return appDatabase
    }

    @Singleton
    @Provides
    fun provideAppDatabase(): AppDatabase {
        return appDatabase
    }


    @Singleton
    @Provides
    fun provideAppDao(): AppDao {
        return appDatabase.appDao()
    }


    @Singleton
    @Provides
    fun provideLocalDataSource(
        appDao: AppDao
    ): LocalDataSource {
        return LocalDataSource(
            appDao
        )
    }


}