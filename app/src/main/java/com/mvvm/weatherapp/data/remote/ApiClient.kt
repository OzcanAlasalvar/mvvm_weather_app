package com.mvvm.weatherapp.data.remote

class ApiClient {

    val weatherApi by lazy {
        WeatherClient.forecastApi
    }
    val cityApi by lazy {
        CityClient.cityApi
    }
}