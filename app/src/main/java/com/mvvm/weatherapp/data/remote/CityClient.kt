package com.mvvm.weatherapp.data.remote

import com.mvvm.weatherapp.BuildConfig
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object CityClient {
    val cityApi: ApiInterface

    init {
        val retrofitCity = Retrofit.Builder()
            .baseUrl(BuildConfig.CITY_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        cityApi = retrofitCity.create(ApiInterface::class.java)
    }
}