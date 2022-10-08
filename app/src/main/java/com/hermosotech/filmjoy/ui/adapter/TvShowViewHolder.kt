package com.hermosotech.filmjoy.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hermosotech.filmjoy.databinding.ItemTvShowBinding
import com.hermosotech.filmjoy.domain.ApiConfiguration
import com.hermosotech.filmjoy.domain.model.TvShow
import com.hermosotech.filmjoy.domain.model.formatFirstAirDate
import java.util.*
import kotlin.math.roundToInt

class TvShowViewHolder(view: View): RecyclerView.ViewHolder(view) {

    val binding = ItemTvShowBinding.bind(view)

    fun render(tvShow: TvShow, apiConfig: ApiConfiguration, onClickListener: (TvShow) -> Unit) {
        binding.tvContentName.text = tvShow.name

        binding.tvContentFirstAirDate.text = tvShow.formatFirstAirDate("dd, MMM yyyy", Locale.US)

        val voteAverage = "${tvShow.voteAverage.roundToInt()}/10"
        binding.tvContentVoteAverage.text = voteAverage

        apiConfig.getImageURL(tvShow.posterPath, 180, ApiConfiguration.ImageType.POSTER)?.let {
            Glide.with(binding.ivContentCoverImage.context).load(it).into(binding.ivContentCoverImage)
        }

        itemView.setOnClickListener { onClickListener(tvShow) }
    }
}