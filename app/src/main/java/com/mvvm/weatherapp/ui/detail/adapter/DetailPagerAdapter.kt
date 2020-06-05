package com.mvvm.weatherapp.ui.detail.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.mvvm.weatherapp.data.model.CityModel
import com.mvvm.weatherapp.ui.detail.DetailFragment

class DetailPagerAdapter(fm: FragmentManager, val cityList: ArrayList<CityModel>) :
    FragmentPagerAdapter(fm, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {


    override fun getItem(position: Int): Fragment {
        return DetailFragment.newInstance(cityList[position].id)
    }

    override fun getCount(): Int = cityList.size


    fun notifyDataSetChanges(list: List<CityModel>) {
        cityList.clear()
        cityList.addAll(list)
        notifyDataSetChanged()
    }
}