package com.iitism.srijan25.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.iitism.srijan25.R
import com.iitism.srijan25.model.EventsData

class EventAdapter(private val events: Array<EventsData>, private val context: Context) :
    RecyclerView.Adapter<EventAdapter.EventViewHolder>() {

    class EventViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val eventName: TextView = view.findViewById(R.id.tvEventName)
        val club: TextView = view.findViewById(R.id.tvOrganiser)
        val prizePool: TextView = view.findViewById(R.id.tvPrizePool)
        val registerButton: Button = view.findViewById(R.id.btn_register)
        val poster: ImageView = view.findViewById(R.id.poster)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.event_card_view, parent, false)
        return EventViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = events[position]
        holder.eventName.text = event.eventName
        holder.club.text = event.organiser
        holder.prizePool.text = context.getString(R.string.prize_pool, event.prizePool)
        holder.registerButton.setOnClickListener {
            event.unstopLink?.let { link ->
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
                context.startActivity(intent)
            }
        }
        if (!event.posterUrl.isNullOrEmpty()) {
            Glide.with(context)
                .load(event.posterUrl)
                .placeholder(R.drawable.concetto)
                .error(R.drawable.concetto)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .into(holder.poster)
        } else {
            holder.poster.setImageResource(R.drawable.concetto)
        }
    }

    override fun getItemCount(): Int {
        return events.size
    }
}
