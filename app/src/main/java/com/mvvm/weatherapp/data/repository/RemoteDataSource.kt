package com.mvvm.weatherapp.data.repository

import com.mvvm.weatherapp.data.model.CityModel
import com.mvvm.weatherapp.data.model.ForecastModel
import io.reactivex.Observable
import io.reactivex.Single

interface RemoteDataSource {

    fun fetchCities(): Observable<List<CityModel>>

    fun fetchForeCast(
        lat: String,
        long: String,
        ex: String,
        format: String,
        appId: String
    ): Single<ForecastModel>
}