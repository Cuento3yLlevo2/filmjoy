package com.hermosotech.filmjoy.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hermosotech.filmjoy.core.di.RoomModule.POPULAR_TV_SHOW_TABLE_NAME
import com.hermosotech.filmjoy.core.di.RoomModule.TOP_RATED_TV_SHOW_TABLE_NAME
import com.hermosotech.filmjoy.databinding.FragmentHomeBinding
import com.hermosotech.filmjoy.domain.ApiConfiguration
import com.hermosotech.filmjoy.domain.model.ApiConfig
import com.hermosotech.filmjoy.domain.model.TvShow
import com.hermosotech.filmjoy.ui.adapter.TvShowAdapter
import com.hermosotech.filmjoy.ui.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var apiConfig : ApiConfig

    private var _binding: FragmentHomeBinding? = null
    private var splashScreen:  SplashScreen? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel.onCreate()

        homeViewModel.popularTvShows.observe(viewLifecycleOwner) { tvShows ->
            initRecycleView(tvShows, homeViewModel.apiConfiguration, binding.rvPopularTvShows, POPULAR_TV_SHOW_TABLE_NAME)
        }

        homeViewModel.topRatedTvShows.observe(viewLifecycleOwner) { tvShows ->
            initRecycleView(tvShows, homeViewModel.apiConfiguration, binding.rvTopTvShows, TOP_RATED_TV_SHOW_TABLE_NAME)
        }

        homeViewModel.isLoading.observe(viewLifecycleOwner) {
            (activity as MainActivity).keepSplashScreenOnScreen(false)
        }

    }

    private fun initRecycleView(
        list: List<TvShow>, apiConfig: ApiConfiguration, rv: RecyclerView, tableName: String
    ) {
        rv.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        rv.adapter = TvShowAdapter(list, apiConfig) { tvShow ->
            onItemSelected(tvShow, tableName)
        }
    }

    fun onItemSelected(tvShow: TvShow, tableName: String){
        val action = HomeFragmentDirections.actionHomeFragmentToTvShowDetailFragment(tvShow.id, tableName, tvShow.name)
        findNavController().navigate(action)
    }
}