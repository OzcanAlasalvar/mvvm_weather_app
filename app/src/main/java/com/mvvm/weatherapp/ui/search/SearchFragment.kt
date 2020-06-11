package com.mvvm.weatherapp.ui.search

import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavDirections
import androidx.navigation.Navigation

import com.mvvm.weatherapp.R
import com.mvvm.weatherapp.base.BaseFragment
import com.mvvm.weatherapp.data.model.CityModel
import com.mvvm.weatherapp.databinding.FragmentSearchBinding
import com.mvvm.weatherapp.ui.SharedMainViewModel

class SearchFragment : BaseFragment<FragmentSearchBinding, SharedMainViewModel>(),
    SearchNavigator {

    private lateinit var adapter: SearchAdapter

    override val mViewModel: SharedMainViewModel by activityViewModels()

    override fun getLayoutResId(): Int = R.layout.fragment_search

    override fun init() {
        adapter = SearchAdapter(
            arrayListOf(),
            mViewModel,
            this
        )
        binding.adapter = adapter
        binding.navigator = this

        binding.searchView.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextChange(query: String?): Boolean {
                query?.let {
                    val lower = it.toLowerCase()
                    mViewModel.findCity(lower.capitalize())
                }
                return true
            }

            override fun onQueryTextSubmit(p0: String?): Boolean {
                return true
            }
        })

        observeLiveData()
    }

    private fun observeLiveData() {
        mViewModel.searchedCities.observe(viewLifecycleOwner, Observer { list ->
            list?.let {
                adapter.notifyChanges(it)
            }
        })

    }

    override fun onItemClick(city: CityModel) {
        binding.searchView.clearFocus()
        mViewModel.saveFavorites(city)
        mViewModel.savedFav.observe(viewLifecycleOwner, Observer {
            if (it) {
                mViewModel.getFavorites()
                navigateDirection(SearchFragmentDirections.actionSearchFragmentToListFragment())
            }
        })

    }

    override fun onCancel() {
        navigateDirection(SearchFragmentDirections.actionSearchFragmentToListFragment())
    }

    private fun navigateDirection(action: NavDirections) {
        Navigation.findNavController(this.requireView()).navigate(action)
    }

}
