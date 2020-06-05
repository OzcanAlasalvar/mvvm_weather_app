package com.mvvm.weatherapp.data.repository

import com.mvvm.weatherapp.data.model.CityModel
import io.reactivex.Observable

interface LocalDataSource {

    fun saveCity(city: CityModel): Observable<Long>

    fun saveCities(cities: List<CityModel>)

    fun fetchCities(): Observable<List<CityModel>>

    fun deleteCity(city: CityModel)

    fun clearLocal();

    fun filterByName(cityName: String): Observable<List<CityModel>>

    fun getFavorites(): Observable<List<CityModel>>

    fun getCityWithId(id :Int): Observable<CityModel>

}