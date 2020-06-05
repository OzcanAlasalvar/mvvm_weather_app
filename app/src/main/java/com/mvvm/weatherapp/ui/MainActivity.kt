package com.mvvm.weatherapp.ui

import android.os.Bundle
import com.mvvm.weatherapp.R
import com.mvvm.weatherapp.base.BaseActivity
import com.mvvm.weatherapp.databinding.ActivityMainBinding

class MainActivity :
    BaseActivity<ActivityMainBinding, SharedMainViewModel>(
        SharedMainViewModel::class.java) {

    override fun getLayoutRes(): Int {
        return R.layout.activity_main
    }

    override fun initViewModel(viewModel: SharedMainViewModel) {
        binding.viewModel = viewModel
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.fetchCities()
        binding.executePendingBindings()
    }


}
