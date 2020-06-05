package com.mvvm.weatherapp.ui.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavDirections
import androidx.navigation.Navigation

import com.mvvm.weatherapp.R
import com.mvvm.weatherapp.data.model.CityModel
import com.mvvm.weatherapp.databinding.FragmentSearchBinding
import com.mvvm.weatherapp.ui.SharedMainViewModel

class SearchFragment : Fragment(),
    SearchNavigator {

    private val viewModel: SharedMainViewModel by activityViewModels()
    private lateinit var adapter: SearchAdapter
    private lateinit var binding: FragmentSearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = SearchAdapter(
            arrayListOf(),
            viewModel,
            this
        )
        binding.adapter = adapter
        binding.navigator = this

        binding.searchView.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextChange(query: String?): Boolean {
                query?.let {
                    val lower = it.toLowerCase()
                    viewModel.findCity(lower.capitalize())
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
        viewModel.searchedCities.observe(viewLifecycleOwner, Observer { list ->
            list?.let {
                adapter.notifyChanges(it)
            }
        })

    }

    override fun onItemClick(city: CityModel) {
        binding.searchView.clearFocus()
        viewModel.saveFavorites(city)
        viewModel.savedFav.observe(viewLifecycleOwner, Observer {
            if (it) {
                viewModel.getFavorites()
                navigateDirection(SearchFragmentDirections.actionSearchFragmentToListFragment())
            }
        })

    }

    override fun onCancel() {
        navigateDirection(SearchFragmentDirections.actionSearchFragmentToListFragment())
    }

    private fun navigateDirection(aciton: NavDirections) {
        Navigation.findNavController(this.requireView()).navigate(aciton)
    }

}
