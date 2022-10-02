package com.hermosotech.filmjoy.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.hermosotech.filmjoy.databinding.ActivityMainBinding
import com.hermosotech.filmjoy.ui.viewmodel.TvShowViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity: AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val tvShowViewModel: TvShowViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        tvShowViewModel.onCreate()

        tvShowViewModel.popularTvShows.observe(this) {
            binding.tvHello.text = it[0].name
        }

        tvShowViewModel.tvShow.observe(this) {
            binding.tvHello.text = it.overview
        }

        tvShowViewModel.isLoading.observe(this) {
            // binding.progress.isVisible = it
        }

        binding.tvHello.setOnClickListener {
            tvShowViewModel.getTvShow(3)
        }

    }
}