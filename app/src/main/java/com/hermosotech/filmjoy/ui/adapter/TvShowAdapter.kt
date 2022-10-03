package com.hermosotech.filmjoy.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hermosotech.filmjoy.R
import com.hermosotech.filmjoy.domain.model.ImageConfig
import com.hermosotech.filmjoy.domain.model.TvShow

class TvShowAdapter(
    private val tvShowList: List<TvShow>,
    private val imgConfig: ImageConfig,
    private val onClickListener: (TvShow) -> Unit
): RecyclerView.Adapter<TvShowViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return TvShowViewHolder(layoutInflater.inflate(R.layout.item_tv_show, parent, false))
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        val item = tvShowList[position]
        holder.render(item, imgConfig, onClickListener)
    }

    override fun getItemCount(): Int = tvShowList.size

}