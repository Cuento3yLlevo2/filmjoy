package com.hermosotech.filmjoy.ui.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.splashscreen.SplashScreen
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hermosotech.filmjoy.core.di.RoomModule.POPULAR_TV_SHOW_TABLE_NAME
import com.hermosotech.filmjoy.core.di.RoomModule.TOP_RATED_TV_SHOW_TABLE_NAME
import com.hermosotech.filmjoy.databinding.FragmentHomeBinding
import com.hermosotech.filmjoy.core.ImageHelper
import com.hermosotech.filmjoy.domain.model.TvShow
import com.hermosotech.filmjoy.ui.adapter.TvShowAdapter
import com.hermosotech.filmjoy.ui.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()

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

        homeViewModel.onCreate(view.context)

        homeViewModel.popularTvShows.observe(viewLifecycleOwner) { tvShows ->
            initRecycleView(tvShows, homeViewModel.imageHelper, binding.rvPopularTvShows, POPULAR_TV_SHOW_TABLE_NAME)
        }

        homeViewModel.topRatedTvShows.observe(viewLifecycleOwner) { tvShows ->
            initRecycleView(tvShows, homeViewModel.imageHelper, binding.rvTopTvShows, TOP_RATED_TV_SHOW_TABLE_NAME)
        }

        homeViewModel.isLoading.observe(viewLifecycleOwner) {
            (activity as MainActivity).keepSplashScreenOnScreen(false)
        }

    }

    private fun initRecycleView(
        list: List<TvShow>, imageHelper: ImageHelper, rv: RecyclerView, tableName: String
    ) {
        rv.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        rv.adapter = TvShowAdapter(list, imageHelper) { tvShow ->
            onItemSelected(tvShow, tableName, context)
        }
        (activity as MainActivity).keepProgressBarOnScreen(false)
    }

    private fun onItemSelected(tvShow: TvShow, tableName: String, context: Context?){
        context?.let {
            val action = HomeFragmentDirections.actionHomeFragmentToTvShowDetailFragment(
                tvShowId = tvShow.id,
                tableName = tableName,
                tvShowName = tvShow.name,
                language = homeViewModel.getCurrentLanguage(context),
                uiMode = it.resources.configuration.uiMode
            )

            findNavController().navigate(action)
        }
    }
}