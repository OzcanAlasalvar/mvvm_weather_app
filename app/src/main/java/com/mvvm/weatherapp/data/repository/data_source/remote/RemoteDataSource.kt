package com.mvvm.weatherapp.data.repository.data_source.remote

import com.mvvm.weatherapp.data.model.CityModel
import com.mvvm.weatherapp.data.model.ForecastModel
import com.mvvm.weatherapp.data.remote.CityApiService
import com.mvvm.weatherapp.data.remote.ForeCastApiService
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val cityApiService: CityApiService,
    private val foreCastApiService: ForeCastApiService
) : RemoteSource {

    override fun fetchCities(): Observable<List<CityModel>> {
        return cityApiService.getCityList()
    }

    override fun fetchForeCast(
        lat: String,
        long: String,
        ex: String,
        format: String,
        appId: String
    ): Single<ForecastModel> {
        return foreCastApiService.getWeatherCall(lat, long, ex, format, appId)
    }
}