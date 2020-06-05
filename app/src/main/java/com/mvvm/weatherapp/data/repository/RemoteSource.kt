package com.mvvm.weatherapp.data.repository

import com.mvvm.weatherapp.data.model.CityModel
import com.mvvm.weatherapp.data.model.ForecastModel
import com.mvvm.weatherapp.data.remote.CityClient
import com.mvvm.weatherapp.data.remote.WeatherClient
import io.reactivex.Observable
import io.reactivex.Single

object RemoteSource : RemoteDataSource {

    override fun fetchCities(): Observable<List<CityModel>> {
        return CityClient.cityApi.getCityList()
    }

    override fun fetchForeCast(
        lat: String,
        long: String,
        ex: String,
        format: String,
        appId: String
    ): Single<ForecastModel> {
        return WeatherClient.forecastApi.getWeatherCall(lat, long, ex, format, appId)
    }
}