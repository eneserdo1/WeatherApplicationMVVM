package com.app.weatherapplicationmvvm.ui.MainActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.app.weatherapplicationmvvm.databinding.ActivityMainBinding
import com.app.weatherapplicationmvvm.utils.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel:MainViewModel by viewModels<MainViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupObserver()


    }

    private fun setupObserver() {
        viewModel.getWeather().observe(this, Observer {
            when (it.status) {
                Status.ERROR -> {
                    Toast.makeText(this, "Veri çekilemedi", Toast.LENGTH_LONG).show()
                    binding.progressBar.visibility = View.GONE
                }

                Status.LOADING -> {
                    Toast.makeText(this, "Yükleniyor", Toast.LENGTH_LONG).show()
                    binding.progressBar.visibility = View.VISIBLE

                }

                Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this, "Yükleniyor", Toast.LENGTH_LONG).show()
                    println("Response--- ${it.data}")

                }
            }
        })

    }


}