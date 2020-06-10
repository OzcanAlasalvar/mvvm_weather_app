package com.mvvm.weatherapp.data.repository

import com.mvvm.weatherapp.data.model.CityModel
import com.mvvm.weatherapp.data.model.ForecastModel
import com.mvvm.weatherapp.data.remote.ForeCastApiService
import com.mvvm.weatherapp.data.repository.data_source.local.LocalDataSource
import com.mvvm.weatherapp.data.repository.data_source.remote.RemoteDataSource
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class Repository @Inject constructor(
    private val localSource: LocalDataSource,
    private val remoteSource: RemoteDataSource
) {

    fun fetchCities(): Observable<List<CityModel>> {
        return Observable.concatArray(
            localSource.fetchCities(),
            remoteSource.fetchCities()
                .doOnNext { cities ->
                    cities?.let {
                        saveCities(it)
                    }
                }
                .onErrorResumeNext(Observable.empty())
        )

    }

    private fun saveCities(cities: List<CityModel>) {
        localSource.saveCities(cities)
    }

    fun saveCity(city: CityModel): Observable<Long> {
        return localSource.saveCity(city)
    }

    fun filterCities(cityName: String): Observable<List<CityModel>> {
        return localSource.filterByName(cityName)
    }

    fun getFavorites(): Observable<List<CityModel>> {
        return localSource.getFavorites()
    }

    fun getCityById(id: Int): Observable<CityModel> {
        return localSource.getCityWithId(id)
    }

    fun fetchForeCastResult(
        city: CityModel,
        appId: String
    ): Single<ForecastModel> {
        return remoteSource.fetchForeCast(
            city.lat!!,
            city.lon!!,
            "minutely",
            ForeCastApiService.CELCIUS,
            appId
        )
    }


}