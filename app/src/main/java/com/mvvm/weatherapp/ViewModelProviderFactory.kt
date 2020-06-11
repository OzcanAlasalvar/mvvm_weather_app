package com.mvvm.weatherapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mvvm.weatherapp.data.repository.Repository
import com.mvvm.weatherapp.ui.SharedMainViewModel
import com.mvvm.weatherapp.ui.detail.DetailViewModel
import javax.inject.Inject

class ViewModelProviderFactory @Inject constructor(private val repository: Repository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SharedMainViewModel::class.java))
            return SharedMainViewModel(repository) as T
        else if (modelClass.isAssignableFrom(DetailViewModel::class.java))
            return DetailViewModel(repository) as T
        else
            throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name);
    }
}

