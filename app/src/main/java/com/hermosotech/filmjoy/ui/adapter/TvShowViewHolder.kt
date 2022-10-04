package com.hermosotech.filmjoy.ui.adapter

import android.os.Build
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
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.math.roundToInt

class TvShowViewHolder(view: View): RecyclerView.ViewHolder(view) {

    val binding = ItemTvShowBinding.bind(view)

    fun render(tvShow: TvShow, imgConfig: ImageConfig?, onClickListener: (TvShow) -> Unit) {
        binding.tvContentName.text = tvShow.name

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            val date = formatter.parse(tvShow.firstAirDate)
            val desiredFormat = DateTimeFormatter.ofPattern("dd, MMM yyyy").format(date)
            binding.tvContentFirstAirDate.text = desiredFormat
        } else {
            val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.US)
            val date = formatter.parse(tvShow.firstAirDate)
            val desiredFormat = date?.let { SimpleDateFormat("dd, MMM yyyy", Locale.US).format(it) }
            binding.tvContentFirstAirDate.text = desiredFormat
        }

        val voteAverage = "${tvShow.voteAverage.roundToInt()}/10"
        binding.tvContentVoteAverage.text = voteAverage

        imgConfig?.let {
            val imageURL = it.secureBaseUrl + it.posterSizes[1] + tvShow.posterPath
            Glide.with(binding.ivContentCoverImage.context).load(imageURL).into(binding.ivContentCoverImage)
        }

        itemView.setOnClickListener { onClickListener(tvShow) }
    }
}