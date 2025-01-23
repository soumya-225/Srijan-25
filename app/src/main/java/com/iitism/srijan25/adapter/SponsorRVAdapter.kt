package com.iitism.srijan25.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.iitism.srijan25.model.SponsorData


import com.iitism.srijan25.R
import kotlin.math.max
import kotlin.math.min

class SponsorRVAdapter(
    private var dataList: List<SponsorData>,
    private val onItemClick: (String) -> Unit
) : RecyclerView.Adapter<SponsorRVAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivSponsor: ImageView = view.findViewById(R.id.ivSponsor)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.rv_item_sponsors, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentData = dataList[position]

        if (currentData.image.isNotEmpty()) {
            Glide.with(holder.ivSponsor.context)
                .load(currentData.image)
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.progress_animation)
                        .error(R.drawable.try_later)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                )
                .into(holder.ivSponsor)
        }

//        holder.ivSponsor.setOnClickListener {
//            if (currentData.link.isNotEmpty()) {
//                onItemClick.invoke(currentData.link)
//            }
//        }
    }

    fun setData(newData: List<SponsorData>) {
        val sizeBefore = dataList.size
        dataList = newData
        val sizeAfter = newData.size
        notifyItemRangeChanged(0, min(sizeBefore, sizeAfter))
        notifyItemRangeInserted(
            min(sizeBefore, sizeAfter),
            max(sizeBefore, sizeAfter) - min(sizeBefore, sizeAfter)
        )
        notifyItemRangeRemoved(
            max(sizeBefore, sizeAfter),
            max(sizeBefore, sizeAfter) - min(sizeBefore, sizeAfter)
        )
    }
}