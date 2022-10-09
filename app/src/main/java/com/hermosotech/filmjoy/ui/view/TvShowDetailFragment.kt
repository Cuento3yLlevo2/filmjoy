package com.hermosotech.filmjoy.ui.view

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.hermosotech.filmjoy.databinding.FragmentTvShowDetailBinding
import com.hermosotech.filmjoy.core.ImageHelper
import com.hermosotech.filmjoy.domain.model.TvShow
import com.hermosotech.filmjoy.domain.model.formatFirstAirDate
import com.hermosotech.filmjoy.ui.viewmodel.TvShowDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import kotlin.math.roundToInt

@AndroidEntryPoint
class TvShowDetailFragment : Fragment() {

    private val tvShowDetailViewModel: TvShowDetailViewModel by viewModels()
    private val args : TvShowDetailFragmentArgs by navArgs()
    private lateinit var tvShow : TvShow
    private lateinit var genres : List<String>

    private var _binding: FragmentTvShowDetailBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        activity?.let {
            (it as MainActivity).keepProgressBarOnScreen(true)
        }
        _binding = FragmentTvShowDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tvShowId: Int = args.tvShowId
        val tableName: String = args.tableName
        val language: String = args.language
        val uiMode: Int = args.uiMode

        if (language != tvShowDetailViewModel.getCurrentLanguage(view.context) || uiMode != view.context.resources.configuration.uiMode) {
            findNavController().navigateUp()
        }

        if (tvShowId >= 0 && tableName.isNotEmpty()) {
            tvShowDetailViewModel.onCreate(view.context, tvShowId, tableName)

            tvShowDetailViewModel.tvShow.observe(viewLifecycleOwner) { tvShowResut ->
                tvShow = tvShowResut

                tvShow.backdropPath?.let { path ->

                    tvShowDetailViewModel.getBitmapFileFromStorageOrNull(binding.ivTvShowCoverImage.context, path)?.let { file ->
                        Glide.with(binding.ivTvShowCoverImage.context)
                            .load(Uri.fromFile(file))
                            .placeholder(ColorDrawable(Color.GRAY))
                            .into(binding.ivTvShowCoverImage)
                    } ?: run {
                        var bitmap : Bitmap? = null

                        tvShowDetailViewModel.getImageURL(path, null, ImageHelper.ImageType.BACKDROP)?.let { apiImageURL ->

                            Glide.with(binding.ivTvShowCoverImage.context)
                                .asBitmap()
                                .load(apiImageURL)
                                .placeholder(ColorDrawable(Color.GRAY))
                                .into(object : CustomTarget<Bitmap>(){
                                    override fun onLoadCleared(placeholder: Drawable?) {}
                                    override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                                        bitmap = resource
                                        binding.ivTvShowCoverImage.setImageBitmap(bitmap)
                                    }
                                })

                            bitmap?.let { image ->
                                tvShowDetailViewModel.storageBitmapInCacheDir(binding.ivTvShowCoverImage.context, path, image)
                            }
                        }
                    }
                }

                binding.tvTvShowFirstAirDate.text = tvShow.formatFirstAirDate("dd, MMM yyyy", binding.tvTvShowFirstAirDate.textLocale)

                val voteAverage = "${tvShow.voteAverage.roundToInt()}/10"
                binding.tvTvShowTitle.text = tvShow.name
                binding.tvTvShowDesc.text = tvShow.overview
                binding.tvTvShowVoteAverage.text = voteAverage
                binding.tvTvShowVoteCount.text = tvShow.voteCount.toString()

                if (tvShow.genreIds.isNotEmpty()) tvShowDetailViewModel.getGenresById(tvShow.genreIds)

                activity?.let {
                    (it as MainActivity).keepProgressBarOnScreen(false)
                }
            }

            tvShowDetailViewModel.genres.observe(viewLifecycleOwner) { listOfGenres ->
                genres = listOfGenres
                val genresText = genres.joinToString(", ")
                binding.tvTvShowGenres.text = genresText
            }
        }
    }

}