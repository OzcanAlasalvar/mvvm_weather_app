package com.mvvm.weatherapp.ui

import androidx.lifecycle.MutableLiveData
import com.mvvm.weatherapp.base.BaseViewModel
import com.mvvm.weatherapp.data.model.CityModel
import com.mvvm.weatherapp.data.repository.Repository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SharedMainViewModel(private val repository: Repository) :
    BaseViewModel() {

    val cities = MutableLiveData<List<CityModel>>() //all cities from respository
    val searchedCities = MutableLiveData<List<CityModel>>() //search result
    val favorites = MutableLiveData<List<CityModel>>() //favorites
    val savedFav = MutableLiveData<Boolean>() //saved data

    fun fetchCities() {
        val dp = repository.fetchCities()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                cities.value = it
            }
        disposable.add(dp);
    }

    fun findCity(cityName: String) {
        val db = repository.filterCities(cityName)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                searchedCities.value = it
            }
        disposable.add(db)
    }

    fun getFavorites() {
        val db = repository.getFavorites()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                favorites.value = it
            }
        disposable.add(db)
    }

    fun saveFavorites(city: CityModel) {
        city.added = 1
        savedFav.value = false
        val db = repository.saveCity(city)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                it.let {
                    savedFav.value = true;
                }
            }
        disposable.add(db)
    }


    override fun onCleared() {
        super.onCleared()
    }
}