package com.mvvm.weatherapp.ui.citylist

import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation

import com.mvvm.weatherapp.R
import com.mvvm.weatherapp.base.BaseFragment
import com.mvvm.weatherapp.data.model.CityModel
import com.mvvm.weatherapp.databinding.FragmentListBinding
import com.mvvm.weatherapp.ui.SharedMainViewModel

class ListFragment : BaseFragment<FragmentListBinding, SharedMainViewModel>(), ListNavigator {

    private val adapter: CityListAdapter = CityListAdapter(arrayListOf(), this)

    override val mViewModel: SharedMainViewModel by activityViewModels()

    override fun getLayoutResId(): Int = R.layout.fragment_list

    override fun init() {
        binding.adapter = adapter
        binding.navigator = this
        mViewModel.getFavorites()
        observeLiveData()
    }

    private fun observeLiveData() {
        mViewModel.favorites.observe(viewLifecycleOwner, Observer { list ->
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

    override fun goDetail(city: CityModel,position: Int) {
        val action =
            ListFragmentDirections.actionListFragmentToRootDetailFragment(position)
        Navigation.findNavController(this.requireView()).navigate(action)
    }

}
