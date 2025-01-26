package com.iitism.srijan25.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.iitism.srijan25.R
import com.iitism.srijan25.model.EventNew

class EventsAdapter(private val events: List<EventNew>, private val onItemClick:(EventNew)->Unit) :
    RecyclerView.Adapter<EventsAdapter.EventViewHolder>() {

    inner class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val eventName: TextView = itemView.findViewById(R.id.tv_title)
        val eventPoster: ImageView = itemView.findViewById(R.id.iv_event_poster)
        val time: TextView = itemView.findViewById(R.id.tv_event_time)
        val venue: TextView = itemView.findViewById(R.id.tv_event_venue)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_rv_events, parent, false)
        return EventViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = events[position]

        holder.eventName.text = event.name
        holder.time.text = event.timeFormatted
        holder.venue.text = event.venue

        Glide.with(holder.itemView.context)
            .load(event.posterUrl)
            .placeholder(R.drawable.progress_animation)
            .error(R.drawable.srijan25_logo_blackbg)
            .into(holder.eventPoster)

        holder.itemView.setOnClickListener {
            onItemClick(event)
        }
    }

    override fun getItemCount(): Int {
        return events.size
    }
}
