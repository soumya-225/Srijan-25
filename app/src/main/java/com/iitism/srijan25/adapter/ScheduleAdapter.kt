package com.iitism.srijan25.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.iitism.srijan25.R
import com.iitism.srijan25.databinding.ItemRvScheduleBinding
import com.iitism.srijan25.model.X1

class ScheduleAdapter(private val eventList: List<X1>) :
    RecyclerView.Adapter<ScheduleAdapter.EventViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val binding =
            ItemRvScheduleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EventViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = eventList[position]
        holder.bind(event)
    }

    override fun getItemCount(): Int = eventList.size

    inner class EventViewHolder(private val binding: ItemRvScheduleBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(event: X1) {
            binding.tvTitle.text = event.name
            binding.tvEventTime.text = event.timeFormatted
            binding.tvEventVenue.text = "Venue: ${event.venue}"

            Glide.with(binding.root).load(event.posterUrl)
                .placeholder(R.drawable.progress_animation).error(R.drawable.srijan25_logo_blackbg)
                .into(binding.ivEventPoster)

            binding.btnRegister.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(event.registerLink))
                itemView.context.startActivity(intent)
            }
        }
    }
}
