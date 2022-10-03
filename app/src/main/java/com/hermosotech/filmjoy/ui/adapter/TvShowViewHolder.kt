package com.hermosotech.filmjoy.ui.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hermosotech.filmjoy.R
import com.hermosotech.filmjoy.core.di.NetworkModule.API_BASE_URL
import com.hermosotech.filmjoy.domain.model.ImageConfig
import com.hermosotech.filmjoy.domain.model.TvShow
import kotlin.math.roundToInt

class TvShowViewHolder(view: View, private val imgConfig: ImageConfig?): RecyclerView.ViewHolder(view) {

    val tvShowName = view.findViewById<TextView>(R.id.tvContentName)
    val tvShowDate = view.findViewById<TextView>(R.id.tvContentFirstAirDate)
    val tvShowVoteAverage = view.findViewById<TextView>(R.id.tvContentVoteAverage)
    val tvShowCover = view.findViewById<ImageView>(R.id.ivContentCoverImage)

    fun render(tvShow: TvShow) {
        tvShowName.text = tvShow.name
        tvShowDate.text = tvShow.firstAirDate
        val voteAverage = "${tvShow.voteAverage.roundToInt()}/10"
        tvShowVoteAverage.text = voteAverage

        imgConfig?.let {
            val imageURL = it.secureBaseUrl + it.posterSizes[1] + tvShow.posterPath
            Glide.with(tvShowCover.context).load(imageURL).into(tvShowCover)
        }

    }
}