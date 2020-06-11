package com.mvvm.weatherapp.ui.citylist

import com.mvvm.weatherapp.data.model.CityModel

interface ListNavigator {

    fun goSearch()

    fun goDetail(city: CityModel, position: Int)
}