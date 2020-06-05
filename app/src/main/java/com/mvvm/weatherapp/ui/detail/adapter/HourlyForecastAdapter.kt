package com.mvvm.weatherapp.ui.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mvvm.weatherapp.R
import com.mvvm.weatherapp.data.model.Hourly
import com.mvvm.weatherapp.databinding.HourlyItemLayoutBinding

class HourlyForecastAdapter(val hourlyForecast: ArrayList<Hourly>) :
    RecyclerView.Adapter<HourlyForecastAdapter.ViewHolder>() {

    class ViewHolder(var binding: HourlyItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(forecast: Hourly) {
            binding.forecast = forecast
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<HourlyItemLayoutBinding>(
            inflater,
            R.layout.hourly_item_layout,
            parent,
            false
        )

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return hourlyForecast.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(hourlyForecast[position])
    }

    fun notifyDataSetChanged(list: List<Hourly>) {
        hourlyForecast.clear()
        hourlyForecast.addAll(list)
        notifyDataSetChanged()
    }
}