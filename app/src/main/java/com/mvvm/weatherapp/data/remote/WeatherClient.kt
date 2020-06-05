package com.mvvm.weatherapp.data.remote

import com.mvvm.weatherapp.BuildConfig
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object WeatherClient {

    val forecastApi: ApiInterface

    init {
        val retrofitWeather = Retrofit.Builder()
            .baseUrl(BuildConfig.WEATHER_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        forecastApi = retrofitWeather.create(ApiInterface::class.java)
    }
}