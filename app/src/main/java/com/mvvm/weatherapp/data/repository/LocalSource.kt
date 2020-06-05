package com.mvvm.weatherapp.data.repository

import com.mvvm.weatherapp.App
import com.mvvm.weatherapp.data.local.AppDao
import com.mvvm.weatherapp.data.local.AppDatabase
import com.mvvm.weatherapp.data.model.CityModel
import io.reactivex.Observable

object LocalSource : LocalDataSource {

    private val dao: AppDao = AppDatabase(App.INSTANCE).appDao()

    override fun saveCity(city: CityModel): Observable<Long> {
        return Observable.fromCallable {
            dao.insert(city)
        }
    }

    override fun saveCities(cities: List<CityModel>) {
        dao.insertAll(cities)
    }

    override fun fetchCities(): Observable<List<CityModel>> {
        return Observable.fromCallable {
            dao.fetchCities()
        }
    }

    override fun deleteCity(city: CityModel) {
        dao.delete(city)
    }

    override fun clearLocal() {
        dao.deleteCityDB()
    }

    override fun filterByName(cityName: String): Observable<List<CityModel>> {
        return Observable.fromCallable {
            dao.searchByName("%$cityName%")
        }
    }

    override fun getFavorites(): Observable<List<CityModel>> {
        return Observable.fromCallable {
            dao.getFavourites()
        }
    }

    override fun getCityWithId(id: Int): Observable<CityModel> {
        return Observable.fromCallable {
            dao.getCityWithId(id)
        }
    }

}