package com.mvvm.weatherapp.ui.citylist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController

import com.mvvm.weatherapp.R
import com.mvvm.weatherapp.data.model.CityModel
import com.mvvm.weatherapp.databinding.FragmentListBinding
import com.mvvm.weatherapp.ui.SharedMainViewModel

class ListFragment : Fragment(), ListNavigator {

    private val viewModel: SharedMainViewModel by activityViewModels()
    private lateinit var binding: FragmentListBinding
    private val adapter: CityListAdapter = CityListAdapter(arrayListOf(), this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.adapter = adapter
        binding.navigator = this
        viewModel.getFavorites()

        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.favorites.observe(viewLifecycleOwner, Observer { list ->
            list?.let {
                binding.adapter!!.notifyChanges(it)
            }
        })
    }

    override fun goSearch() {
        val action =
            ListFragmentDirections.actionListFragmentToSearchFragment()
        Navigation.findNavController(this.requireView()).navigate(action)
    }

    override fun goDetail(city: CityModel) {
        val action =
            ListFragmentDirections.actionListFragmentToRootDetailFragment()
        Navigation.findNavController(this.requireView()).navigate(action)
    }

}
