package com.app.weatherapplicationmvvm.ui.MainActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.weatherapplicationmvvm.model.WeatherResponse
import com.app.weatherapplicationmvvm.databinding.ActivityMainBinding
import com.app.weatherapplicationmvvm.utils.Constants.Companion.IMAGE_URL
import com.app.weatherapplicationmvvm.model.Status
import com.app.weatherapplicationmvvm.utils.calculatCelcius
import com.app.weatherapplicationmvvm.utils.getDateTime
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel:MainViewModel by viewModels<MainViewModel>()
    private lateinit var adapter: HourlyRecylerviewAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupObserver()
        initHourlyRecyclerview()
        buttonsListener()

    }

    private fun buttonsListener() {
        binding.refreshButton.setOnClickListener {
            viewModel.fetchWeather()
        }
    }

    private fun initHourlyRecyclerview() {
        adapter = HourlyRecylerviewAdapter()
        binding.hourlyWeatherRecyclerview.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        binding.hourlyWeatherRecyclerview.adapter = adapter
    }


    private fun setupObserver() {
        viewModel.getWeather().observe(this, Observer {
            when (it.status) {

                Status.ERROR -> {
                    Toast.makeText(this, "Veri çekilemedi", Toast.LENGTH_LONG).show()
                    binding.progressBar.visibility = View.GONE
                }

                Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                }

                Status.SUCCESS -> {
                    initUI(it.data as WeatherResponse)
                    println("Response -- ${it.data}")
                    adapter.setList(it.data.hourly)
                    binding.progressBar.visibility = View.GONE
                }

            }
        })
    }


    private fun initUI(data:WeatherResponse) {
        binding.apply {
            Glide.with(this@MainActivity).load("$IMAGE_URL${data.current!!.weather[0].icon}.png").into(weatherIcon)
            temperatureTxt.text = "${calculatCelcius(data.current.temp)}°C"
            weatherDescriptionTxt.text = data.current.weather[0].main
            currentDateTxt.text = getDateTime(data.current.dt,"dd/MM/yy hh:mm")
        }
    }


}