package com.mvvm.weatherapp.data.remote

import com.mvvm.weatherapp.data.model.CityModel
import com.mvvm.weatherapp.data.model.ForecastModel
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    companion object {
        const val ONCE_CALL = "onecall"
        const val APP_ID = "appid"
        const val LAT = "lat"
        const val LONG = "lon"
        const val EXCLUDE = "exclude"
        const val UNIT = "units"
        const val GEOIT = "geoit"
        const val CELCIUS = "metric"
    }


    @GET("city.json")
    fun getCityList(): Observable<List<CityModel>>


    @GET(ONCE_CALL)
    fun getWeatherCall(
        @Query(LAT) lat: String?,
        @Query(LONG) long: String?,
        @Query(EXCLUDE) ex: String?,
        @Query(UNIT) format: String,
        @Query(APP_ID) appId: String
    ): Single<ForecastModel>
}