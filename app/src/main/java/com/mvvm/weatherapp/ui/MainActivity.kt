package com.mvvm.weatherapp.ui

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.mvvm.weatherapp.App
import com.mvvm.weatherapp.R
import com.mvvm.weatherapp.base.BaseActivity
import com.mvvm.weatherapp.databinding.ActivityMainBinding
import com.mvvm.weatherapp.ui.detail.DetailViewModel
import javax.inject.Inject

class MainActivity :
    BaseActivity<ActivityMainBinding, SharedMainViewModel>(
        SharedMainViewModel::class.java
    ) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun getLayoutRes(): Int {
        return R.layout.activity_main
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.INSTANCE.getAppComponent().inject(this)

        getViewModel().fetchCities()
        binding.executePendingBindings()
    }


    override fun getViewModel(): SharedMainViewModel =
        ViewModelProviders.of(this, viewModelFactory).get(SharedMainViewModel::class.java)
}
