package com.mvvm.weatherapp.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mvvm.weatherapp.R
import com.mvvm.weatherapp.data.model.CityModel
import com.mvvm.weatherapp.databinding.SearchItemLayoutBinding
import com.mvvm.weatherapp.ui.SharedMainViewModel

class SearchAdapter(
    val cities: ArrayList<CityModel>,
    val viewModel: SharedMainViewModel,
    val navigator: SearchNavigator
) :
    RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    class ViewHolder(var binding: SearchItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(city: CityModel, viewModel: SharedMainViewModel, navigator: SearchNavigator) {
            binding.model = city
            binding.viewModel = viewModel
            binding.listener = navigator
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val infilater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<SearchItemLayoutBinding>(
            infilater,
            R.layout.search_item_layout,
            parent,
            false
        )
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(cities[position], viewModel, navigator)
    }

    override fun getItemCount(): Int {
        return cities.size
    }

    fun notifyChanges(list: List<CityModel>) {
        cities.clear()
        cities.addAll(list)
        notifyDataSetChanged()
    }
}