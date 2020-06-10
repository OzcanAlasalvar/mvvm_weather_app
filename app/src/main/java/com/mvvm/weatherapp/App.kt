package com.mvvm.weatherapp

import android.app.Application
import com.mvvm.weatherapp.di.component.AppComponent
import com.mvvm.weatherapp.di.component.DaggerAppComponent
import com.mvvm.weatherapp.di.module.ApplicationModule
import com.mvvm.weatherapp.di.module.DBModule

class App : Application() {
    private lateinit var component: AppComponent

    companion object {
        lateinit var INSTANCE: App
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        component = DaggerAppComponent.builder()
            .applicationModule(ApplicationModule(this))
            .dBModule(DBModule(this))
            .build()
        component.inject(this)
    }

    fun getAppComponent(): AppComponent {
        return component
    }
}