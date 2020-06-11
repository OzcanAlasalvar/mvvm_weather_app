package com.mvvm.weatherapp.ui.citylist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mvvm.weatherapp.R
import com.mvvm.weatherapp.data.model.CityModel
import com.mvvm.weatherapp.databinding.ListCityItemBinding

class CityListAdapter(val cityList: ArrayList<CityModel>, val listener: ListNavigator) :
    RecyclerView.Adapter<CityListAdapter.ViewHolder>() {


    class ViewHolder(var binding: ListCityItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(model: CityModel, listener: ListNavigator, position: Int) {
            binding.model = model
            binding.position = position
            binding.listener = listener
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ListCityItemBinding>(
            inflater,
            R.layout.list_city_item,
            parent,
            false
        )
        return ViewHolder(
            binding
        )
    }

    override fun getItemCount(): Int = cityList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(cityList[position], listener, position)
    }

    fun notifyChanges(list: List<CityModel>) {
        cityList.clear()
        cityList.addAll(list)
        notifyDataSetChanged()
    }
}