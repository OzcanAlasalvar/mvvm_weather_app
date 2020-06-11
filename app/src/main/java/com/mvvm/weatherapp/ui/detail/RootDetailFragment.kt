package com.mvvm.weatherapp.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.mvvm.weatherapp.R
import com.mvvm.weatherapp.databinding.FragmentRootDetailBinding
import com.mvvm.weatherapp.ui.SharedMainViewModel
import com.mvvm.weatherapp.ui.citylist.ListFragmentDirections
import com.mvvm.weatherapp.ui.detail.adapter.DetailPagerAdapter

class RootDetailFragment : Fragment(), DetailsNavigation {

    private val viewModel: SharedMainViewModel by activityViewModels()
    private lateinit var binding: FragmentRootDetailBinding
    private lateinit var pagerAdapter: DetailPagerAdapter

    private var index: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val inflater = LayoutInflater.from(context)
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_root_detail,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            index = RootDetailFragmentArgs.fromBundle(it).selectedCity
        }

        pagerAdapter = DetailPagerAdapter(childFragmentManager, arrayListOf())
        binding.adapter = pagerAdapter
        binding.navigator = this

        viewModel.getFavorites()
        observeLiveData()
    }

    fun observeLiveData() {
        viewModel.favorites.observe(viewLifecycleOwner, Observer { list ->
            list?.let {
                binding.viewPager.offscreenPageLimit = it.size
                pagerAdapter.notifyDataSetChanges(it)
                binding.viewPager.currentItem = index
            }
        })
    }

    override fun goList() {
        val action = RootDetailFragmentDirections.actionRootDetailFragmentToListFragment()
        Navigation.findNavController(this.requireView()).navigate(action)
    }

}