package com.mvvm.weatherapp.ui.search

import com.mvvm.weatherapp.data.model.CityModel

interface SearchNavigator {
    fun onItemClick(city : CityModel)

    fun onCancel();
}