package com.mvvm.weatherapp.ui.detail

import androidx.lifecycle.MutableLiveData
import com.mvvm.weatherapp.base.BaseViewModel
import com.mvvm.weatherapp.data.model.CityModel
import com.mvvm.weatherapp.data.model.ForecastModel
import com.mvvm.weatherapp.data.repository.Repository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class DetailViewModel(private val repository: Repository) : BaseViewModel() {

    val city = MutableLiveData<CityModel>()
    val forecast = MutableLiveData<ForecastModel>()


    fun refresh(id: Int, appId: String) {
        val dp = repository.getCityById(id)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                it.let {
                    city.value = it
                    getForeCast(it, appId)
                }
            }
        disposable.add(dp)
    }

    fun getForeCast(cityModel: CityModel, appId: String) {
        val dp = repository.fetchForeCastResult(cityModel, appId)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<ForecastModel>() {
                override fun onSuccess(t: ForecastModel) {
                    forecast.value = t
                }

                override fun onError(e: Throwable) {

                }

            })

        disposable.add(dp)
    }


    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }


}