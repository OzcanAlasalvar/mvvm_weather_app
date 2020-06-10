package com.mvvm.weatherapp.di.module

import android.app.Application
import com.mvvm.weatherapp.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: App) {

    @Singleton
    @Provides
    fun provideApplication(): Application {
        return application
    }
}