package com.hermosotech.filmjoy.ui.adapter

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.hermosotech.filmjoy.databinding.ItemTvShowBinding
import com.hermosotech.filmjoy.core.ImageHelper
import com.hermosotech.filmjoy.domain.model.TvShow
import com.hermosotech.filmjoy.domain.model.formatFirstAirDate
import java.util.*
import kotlin.math.roundToInt

class TvShowViewHolder(view: View): RecyclerView.ViewHolder(view) {

    val binding = ItemTvShowBinding.bind(view)

    fun render(tvShow: TvShow, imageHelper: ImageHelper, onClickListener: (TvShow) -> Unit) {
        binding.tvContentName.text = tvShow.name

        binding.tvContentFirstAirDate.text = tvShow.formatFirstAirDate("dd, MMM yyyy", Locale.US)

        val voteAverage = "${tvShow.voteAverage.roundToInt()}/10"
        binding.tvContentVoteAverage.text = voteAverage



        tvShow.posterPath?.let { posterPath ->

            imageHelper.getBitmapFileFromStorageOrNull(binding.ivContentCoverImage.context, posterPath)?.let { file ->
                Glide.with(binding.ivContentCoverImage.context)
                    .load(Uri.fromFile(file))
                    .placeholder(ColorDrawable(Color.GRAY))
                    .into(binding.ivContentCoverImage)
            } ?: run {
                var bitmap : Bitmap? = null

                imageHelper.buildApiImageURL(posterPath, 180, ImageHelper.ImageType.POSTER)?.let { apiImageURL ->

                    Glide.with(binding.ivContentCoverImage.context)
                        .asBitmap()
                        .load(apiImageURL)
                        .placeholder(ColorDrawable(Color.GRAY))
                        .into(object : CustomTarget<Bitmap>(){
                            override fun onLoadCleared(placeholder: Drawable?) {}
                            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                                bitmap = resource
                                binding.ivContentCoverImage.setImageBitmap(bitmap)
                            }
                        })

                    bitmap?.let { image ->
                        imageHelper.storageBitmapInCacheDir(binding.ivContentCoverImage.context, posterPath, image)
                    }
                }
            }
        }

        itemView.setOnClickListener { onClickListener(tvShow) }
    }
}