package com.iitism.srijan25.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.iitism.srijan25.R
import com.iitism.srijan25.model.CoreTeam

class CoreTeamAdapter(private val dataList: List<CoreTeam>) :
    RecyclerView.Adapter<CoreTeamAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val profileImage: ImageView = view.findViewById(R.id.profile_image)
        val name: TextView = view.findViewById(R.id.tv_name)
        val position: TextView = view.findViewById(R.id.tv_position)
        val team: TextView = view.findViewById(R.id.tv_team)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.core_team_card, parent, false)
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
                        .placeholder(R.drawable.progress_animation)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .error(R.drawable.ic_person)
                )
                .fitCenter()
                .into(holder.profileImage)
        } else {
            holder.profileImage.setImageResource(R.drawable.ic_person)
        }

        holder.name.text = currentData.name
        holder.team.text = currentData.team
        holder.position.text = currentData.position
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}