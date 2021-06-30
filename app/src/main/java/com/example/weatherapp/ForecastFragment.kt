package com.example.weatherapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.anushka.newsapiclient.data.util.Resource
import com.example.weatherapp.databinding.FragmentForecastBinding
import com.example.weatherapp.presentation.viewmodel.WeatherForecastViewModel
import java.text.SimpleDateFormat
import java.util.*


class ForecastFragment : Fragment() {
    private lateinit var forecastFragmentBinding: FragmentForecastBinding
    private lateinit var viewModel: WeatherForecastViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_forecast, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        forecastFragmentBinding = FragmentForecastBinding.bind(view)
        val args: ForecastFragmentArgs by navArgs()
        val city = args.selectedCity

        viewModel = (activity as MainActivity).viewModel

        getWeatherForecast(city)
        onButtonClick()
    }

    private fun getWeatherForecast(city: String) {
        viewModel.getWeatherForecastByCity(city)

        viewModel.weatherForecast.observe(viewLifecycleOwner, { response ->
            when (response) {
                is Resource.Success -> {
                    response.data?.let {
                        val weatherForecastData = it.data.toList()[0]
                        forecastFragmentBinding.city.text = city
                        forecastFragmentBinding.tempoDescricao.text = weatherForecastData.weather.description
                        forecastFragmentBinding.date.text = forecastFragmentBinding.root.context.getString(R.string.info) + " " + weatherForecastData.datetime.toDate().formatTo("dd/MM/yyyy")
                        forecastFragmentBinding.minTemp.text = weatherForecastData.appMinTemp.toString()
                        forecastFragmentBinding.maxTemp.text = weatherForecastData.appMaxTemp.toString()

                    }

                }
                is Resource.Error -> {
                    response.message?.let {
                        Toast.makeText(activity, "Erro : $it", Toast.LENGTH_LONG)
                            .show()
                    }
                }

            }
        })

    }

    fun String.toDate(dateFormat: String = "yyyy-MM-dd", timeZone: TimeZone = TimeZone.getTimeZone("UTC")): Date {
        val parser = SimpleDateFormat(dateFormat, Locale.getDefault())
        parser.timeZone = timeZone
        return parser.parse(this)
    }

    fun Date.formatTo(dateFormat: String, timeZone: TimeZone = TimeZone.getDefault()): String {
        val formatter = SimpleDateFormat(dateFormat, Locale.getDefault())
        formatter.timeZone = timeZone
        return formatter.format(this)
    }

    private fun onButtonClick() {
        forecastFragmentBinding.btnLeave.setOnClickListener {
            viewModel.setWeatherForecastAsNull()
            findNavController().navigate(
                R.id.action_forecastFragment_to_searchFragment
            )
        }
    }

}