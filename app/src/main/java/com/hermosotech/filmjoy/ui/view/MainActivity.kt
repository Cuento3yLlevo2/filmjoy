package com.hermosotech.filmjoy.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
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
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
            // binding.progress.isVisible = it
        }



        /*
        binding.tvHello.setOnClickListener {
            tvShowViewModel.getTvShow(3)
        }
         */

    }

    private fun initRecycleView(list: List<TvShow>, imgConfig: ImageConfig) {
        val recyclerView = binding.rvTvShows
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        recyclerView.adapter = TvShowAdapter(list, imgConfig)
    }
}