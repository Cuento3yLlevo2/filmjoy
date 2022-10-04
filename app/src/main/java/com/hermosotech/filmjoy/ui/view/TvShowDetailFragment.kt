package com.hermosotech.filmjoy.ui.view

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.hermosotech.filmjoy.core.di.RoomModule
import com.hermosotech.filmjoy.databinding.FragmentTvShowDetailBinding
import com.hermosotech.filmjoy.domain.model.ApiConfig
import com.hermosotech.filmjoy.domain.model.TvShow
import com.hermosotech.filmjoy.ui.viewmodel.TvShowDetailViewModel
import com.hermosotech.filmjoy.ui.viewmodel.TvShowsViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.math.roundToInt

@AndroidEntryPoint
class TvShowDetailFragment : Fragment() {

    private val tvShowDetailViewModel: TvShowDetailViewModel by viewModels()
    private val args : TvShowDetailFragmentArgs by navArgs()
    private lateinit var apiConfig : ApiConfig
    private lateinit var tvShow : TvShow

    private var _binding: FragmentTvShowDetailBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTvShowDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tvShowId: Int = args.tvShowId
        val tableName: String = args.tableName

        if (tvShowId >= 0 && tableName.isNotEmpty()) {
            tvShowDetailViewModel.onCreate(tvShowId, tableName)

            tvShowDetailViewModel.apiConfiguration.observe(viewLifecycleOwner){
                apiConfig = it
            }

            tvShowDetailViewModel.tvShow.observe(viewLifecycleOwner) { tvShowResut ->
                tvShow = tvShowResut

                apiConfig.imageConfig?.let {
                    val imageURL = it.secureBaseUrl + it.posterSizes[4] + tvShow.posterPath
                    Glide.with(binding.ivTvShowCoverImage.context).load(imageURL).into(binding.ivTvShowCoverImage)
                }

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                    val date = formatter.parse(tvShow.firstAirDate)
                    val desiredFormat = DateTimeFormatter.ofPattern("dd, MMM yyyy").format(date)
                    binding.tvTvShowFirstAirDate.text = desiredFormat
                } else {
                    val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.US)
                    val date = formatter.parse(tvShow.firstAirDate)
                    val desiredFormat = date?.let { SimpleDateFormat("dd, MMM yyyy", Locale.US).format(it) }
                    binding.tvTvShowFirstAirDate.text = desiredFormat
                }

                val voteAverage = "${tvShow.voteAverage.roundToInt()}/10"
                binding.tvTvShowTitle.text = tvShow.name
                binding.tvTvShowDesc.text = tvShow.overview
                binding.tvTvShowVoteAverage.text = voteAverage
                binding.tvTvShowVoteCount.text = tvShow.voteCount.toString()

                // tvShow.genreIds Todo call to api to retrieved genre by iD
            }

            tvShowDetailViewModel.isLoading.observe(viewLifecycleOwner) {
                // todo progress bar
            }
        }
    }

}