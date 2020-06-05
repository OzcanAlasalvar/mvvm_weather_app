package com.mvvm.weatherapp.data.repository

import com.mvvm.weatherapp.data.model.CityModel
import com.mvvm.weatherapp.data.model.ForecastModel
import com.mvvm.weatherapp.data.remote.ApiInterface
import io.reactivex.Observable
import io.reactivex.Single

class Repository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) {

    fun fetchCities(): Observable<List<CityModel>> {
        return Observable.concatArray(
            localDataSource.fetchCities(),
            remoteDataSource.fetchCities()
                .doOnNext { cities ->
                    cities?.let {
                        saveCities(it)
                    }
                }
                .onErrorResumeNext(Observable.empty())
        )

    }

    private fun saveCities(cities: List<CityModel>) {
        localDataSource.saveCities(cities)
    }

    fun saveCity(city: CityModel): Observable<Long> {
        return localDataSource.saveCity(city)
    }

    fun filterCities(cityName: String): Observable<List<CityModel>> {
        return localDataSource.filterByName(cityName)
    }

    fun getFavorites(): Observable<List<CityModel>> {
        return localDataSource.getFavorites()
    }

    fun getCityById(id: Int): Observable<CityModel> {
        return localDataSource.getCityWithId(id)
    }

    fun fetchForeCastResult(
        city: CityModel,
        appId: String
    ): Single<ForecastModel> {
        return remoteDataSource.fetchForeCast(
            city.lat!!,
            city.lon!!,
            "minutely",
            ApiInterface.CELCIUS,
            appId
        )
    }


}