package com.mvvm.weatherapp.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.mvvm.weatherapp.App
import com.mvvm.weatherapp.R
import com.mvvm.weatherapp.base.BaseFragment
import com.mvvm.weatherapp.databinding.FragmentDetailBinding
import com.mvvm.weatherapp.ui.detail.adapter.DailyForecastAdapter
import com.mvvm.weatherapp.ui.detail.adapter.HourlyForecastAdapter
import javax.inject.Inject

class DetailFragment :Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private var cityID: Int = 0
    private lateinit var binding: FragmentDetailBinding
    private lateinit var viewModel: DetailViewModel

    private val hourlyAdapter = HourlyForecastAdapter(arrayListOf())
    private val dailyAdapter = DailyForecastAdapter(arrayListOf())

    companion object {

        const val ARGUMENT_NAME = "cityId"

        fun newInstance(cityId: Int) = DetailFragment()
            .apply {
                arguments = Bundle().apply {
                    putInt(ARGUMENT_NAME, cityId)
                }
            }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getInt(ARGUMENT_NAME)?.let {
            cityID = it
        }
        App.INSTANCE.getAppComponent().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val inflater = LayoutInflater.from(context)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(cityID.toString(), DetailViewModel::class.java)
        binding.dailyAdapter = dailyAdapter
        binding.hourlyAdapter = hourlyAdapter

        viewModel.refresh(cityID, getString(R.string.api_id))

        observeLiveData()
    }

    fun observeLiveData() {

        viewModel.city.observe(viewLifecycleOwner, Observer { city ->
            city?.let {
                binding.city = it
            }
        })

        viewModel.forecast.observe(viewLifecycleOwner, Observer { forecast ->
            forecast?.let {
                binding.forecastModel = it
                val hourlyList = it.hourly
                hourlyList?.let { list ->
                    hourlyAdapter.notifyDataSetChanged(list)
                }

                val dailyList = it.daily
                dailyList?.let { list ->
                    dailyAdapter.notifyDataSetChanged(list)
                }

            }
        })
    }




}