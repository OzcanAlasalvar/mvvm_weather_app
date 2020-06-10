package com.mvvm.weatherapp.data.remote

import com.mvvm.weatherapp.data.model.CityModel
import io.reactivex.Observable
import retrofit2.http.GET

interface CityApiService {

    @GET("city.json")
    fun getCityList(): Observable<List<CityModel>>
}