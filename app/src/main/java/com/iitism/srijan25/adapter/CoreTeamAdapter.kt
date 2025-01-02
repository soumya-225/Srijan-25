package com.iitism.srijan25.adapter

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.iitism.srijan25.Data.CoreTeamDataModel
import com.iitism.srijan25.R

class CoreTeamAdapter(private val dataList: List<CoreTeamDataModel>) :
    RecyclerView.Adapter<CoreTeamAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.coreteam_card_view, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setIsRecyclable(false)
        val currentData = dataList[position]

        if (!currentData.image.isNullOrEmpty()) {
            Glide.with(holder.itemView)
                .load(currentData.image)
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.ic_person)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                )
                .fitCenter()
                .into(holder.profileImage)
        } else {
            holder.profileImage.setImageResource(R.drawable.ic_person)
        }

        holder.name.text = currentData.name
        holder.team.text = currentData.team
        holder.position.text = currentData.position

        holder.linkedInUrl.setOnClickListener {
            val url = currentData.linkedIn_url
            if (!url.isNullOrEmpty()) {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                ContextCompat.startActivity(it.context, intent, null)
            } else {
                Log.d("LinkedIn URL", "URL is null or empty")
            }
        }

        holder.instagramUrl.setOnClickListener {
            val url = currentData.instagram_url
            if (!url.isNullOrEmpty()) {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                ContextCompat.startActivity(it.context, intent, null)
            } else {
                Log.d("Instagram URL", "URL is null or empty")
            }
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val profileImage: ImageView = view.findViewById(R.id.profile_image)
        val name: TextView = view.findViewById(R.id.tv_name)
        val position: TextView = view.findViewById(R.id.tv_position)
        val team: TextView = view.findViewById(R.id.tv_team)
        val linkedInUrl: ImageView = view.findViewById(R.id.image_linkedIn)
        val instagramUrl: ImageView = view.findViewById(R.id.image_instagram)
    }
}