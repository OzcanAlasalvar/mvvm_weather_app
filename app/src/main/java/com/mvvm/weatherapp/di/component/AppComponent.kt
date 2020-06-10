package com.mvvm.weatherapp.di.component

import android.app.Application
import com.mvvm.weatherapp.App
import com.mvvm.weatherapp.di.module.ApplicationModule
import com.mvvm.weatherapp.di.module.DBModule
import com.mvvm.weatherapp.di.module.NetworkModule
import com.mvvm.weatherapp.di.module.RepositoryModule
import com.mvvm.weatherapp.ui.MainActivity
import com.mvvm.weatherapp.ui.detail.DetailFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [ApplicationModule::class, NetworkModule::class, DBModule::class, RepositoryModule::class])
interface AppComponent {

    fun inject(application: App)
    fun inject(mainActivity: MainActivity)
    fun inject(detailFragment: DetailFragment)

    fun application(): Application

}