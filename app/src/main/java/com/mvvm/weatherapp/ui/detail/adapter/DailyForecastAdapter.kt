package com.mvvm.weatherapp.ui.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mvvm.weatherapp.R
import com.mvvm.weatherapp.data.model.Daily
import com.mvvm.weatherapp.databinding.ItemDailyLayoutBinding

class DailyForecastAdapter(val dailyList: ArrayList<Daily>) :
    RecyclerView.Adapter<DailyForecastAdapter.DailyViewHolder>() {

    class DailyViewHolder(var binding: ItemDailyLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(forecast: Daily) {
            binding.forecast = forecast
            binding.executePendingBindings()
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ItemDailyLayoutBinding>(
            inflater,
            R.layout.item_daily_layout,
            parent,
            false
        )

        return DailyViewHolder(view)
    }

    override fun getItemCount(): Int = dailyList.size

    override fun onBindViewHolder(holder: DailyViewHolder, position: Int) {
        holder.bind(dailyList[position])
    }

    fun notifyDataSetChanged(list: List<Daily>) {
        dailyList.clear()
        dailyList.addAll(list)
        notifyDataSetChanged()
    }

}