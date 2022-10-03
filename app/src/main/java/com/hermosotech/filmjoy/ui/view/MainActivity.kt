package com.hermosotech.filmjoy.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hermosotech.filmjoy.databinding.ActivityMainBinding
import com.hermosotech.filmjoy.domain.model.ApiConfig
import com.hermosotech.filmjoy.domain.model.ImageConfig
import com.hermosotech.filmjoy.domain.model.TvShow
import com.hermosotech.filmjoy.ui.adapter.TvShowAdapter
import com.hermosotech.filmjoy.ui.viewmodel.TvShowViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity: AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val tvShowViewModel: TvShowViewModel by viewModels()
    private lateinit var apiConfig : ApiConfig

    override fun onCreate(savedInstanceState: Bundle?) {
        val screenSplash = installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        screenSplash.setKeepOnScreenCondition { true }

        tvShowViewModel.onCreate()

        tvShowViewModel.apiConfiguration.observe(this){
            apiConfig = it
        }

        tvShowViewModel.popularTvShows.observe(this) { tvShows ->
            apiConfig.imageConfig?.let { imgConfig ->
                initRecycleView(tvShows, imgConfig)
            }
        }

        tvShowViewModel.isLoading.observe(this) {
            screenSplash.setKeepOnScreenCondition { false }
        }



        /*
        binding.tvHello.setOnClickListener {
            tvShowViewModel.getTvShow(3)
        }
         */

    }

    private fun initRecycleView(list: List<TvShow>, imgConfig: ImageConfig) {
        binding.rvTvShows.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        binding.rvTvShows.adapter = TvShowAdapter(list, imgConfig) { tvShow ->
            onItemSelected(tvShow)
        }
    }

    fun onItemSelected(tvShow: TvShow){

    }
}