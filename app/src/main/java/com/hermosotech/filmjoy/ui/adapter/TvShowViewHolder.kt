package com.hermosotech.filmjoy.ui.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hermosotech.filmjoy.R
import com.hermosotech.filmjoy.core.di.NetworkModule.API_BASE_URL
import com.hermosotech.filmjoy.databinding.ItemTvShowBinding
import com.hermosotech.filmjoy.domain.model.ImageConfig
import com.hermosotech.filmjoy.domain.model.TvShow
import kotlin.math.roundToInt

class TvShowViewHolder(view: View): RecyclerView.ViewHolder(view) {

    val binding = ItemTvShowBinding.bind(view)

    fun render(tvShow: TvShow, imgConfig: ImageConfig?, onClickListener: (TvShow) -> Unit) {
        binding.tvContentName.text = tvShow.name
        binding.tvContentFirstAirDate.text = tvShow.firstAirDate
        val voteAverage = "${tvShow.voteAverage.roundToInt()}/10"
        binding.tvContentVoteAverage.text = voteAverage

        imgConfig?.let {
            val imageURL = it.secureBaseUrl + it.posterSizes[1] + tvShow.posterPath
            Glide.with(binding.ivContentCoverImage.context).load(imageURL).into(binding.ivContentCoverImage)
        }

        itemView.setOnClickListener { onClickListener(tvShow) }
    }
}