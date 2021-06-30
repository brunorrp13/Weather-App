package com.example.weatherapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.anushka.newsapiclient.data.util.Resource
import com.example.weatherapp.databinding.FragmentSearchBinding
import com.example.weatherapp.presentation.viewmodel.WeatherForecastViewModel
import java.util.*

class SearchFragment : Fragment() {

    private lateinit var searchFragmentBinding: FragmentSearchBinding
    private lateinit var viewModel: WeatherForecastViewModel
    private var isLoading = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchFragmentBinding = FragmentSearchBinding.bind(view)
        viewModel = (activity as MainActivity).viewModel
        validateSearch()
    }

    private fun validateSearch() {
        searchFragmentBinding.btnSearch.setOnClickListener {
            var city = searchFragmentBinding.inputCity.text.toString().trim().capitalize(Locale.ROOT)
            if (city == "") {
                Toast.makeText(activity, "Digite uma cidade", Toast.LENGTH_LONG).show()
            } else {
                viewModel.getWeatherForecastByCity(city)
                viewModel.weatherForecast.observe(viewLifecycleOwner, { response ->
                    when (response) {
                        is Resource.Success -> {
                            hideProgressBar()
                            if (currentFragment()) {
                                city = searchFragmentBinding.inputCity.text.toString().trim().capitalize(Locale.ROOT)
                                val bundle = Bundle().apply {
                                    putString("selected_city", city)
                                }
                                findNavController().navigate(
                                    R.id.action_searchFragment_to_forecastFragment,
                                    bundle
                                )
                            }
                        }

                        is Resource.Error -> {
                            hideProgressBar()
                            response.message?.let {
                                Toast.makeText(
                                    activity,
                                    "Cidade não encontrada",
                                    Toast.LENGTH_LONG
                                )
                                    .show()
                            }
                            viewModel.setWeatherForecastAsNull()
                        }
                        is Resource.Loading -> {
                            showProgressBar()
                        }
                    }
                })
            }
        }
    }

    private fun showProgressBar() {
        isLoading = true
        searchFragmentBinding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar(){
        isLoading = false
        searchFragmentBinding.progressBar.visibility  = View.INVISIBLE
    }

    private fun currentFragment(): Boolean {
       if (findNavController().currentDestination?.id == R.id.searchFragment) {
           return true
       }
          return false
    }
}