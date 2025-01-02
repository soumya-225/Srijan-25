package com.iitism.srijan25.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageButton
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.iitism.srijan25.R
import com.iitism.srijan25.models.ScheduleModel

class ScheduleAdapter(private val dataList : List<ScheduleModel>): RecyclerView.Adapter<ScheduleAdapter.MyViewHolder>() {
    class MyViewHolder(timelineView: View): RecyclerView.ViewHolder(timelineView) {
        val timeOnLeft: TextView = timelineView.findViewById(R.id.time_onLeft)
        val timeOnRight : TextView = timelineView.findViewById(R.id.time_onRight)
        val nameOnLeft : TextView = timelineView.findViewById(R.id.event_onLeft)
        val nameOnRight : TextView = timelineView.findViewById(R.id.event_onRight)
        val venueOnLeft : TextView = timelineView.findViewById(R.id.venue_onLeft)
        val venueOnRight : TextView = timelineView.findViewById(R.id.venue_onRight)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val timelineView = LayoutInflater.from(parent.context).inflate(
            R.layout.schedule_card,parent,false
        )
        return MyViewHolder(timelineView)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = dataList[position]
        var venue = currentItem.eventVenue
//        if(venue != null) venue = "-"+ venue
        val time = currentItem.eventTime
        val name = currentItem.eventName
        if(position%2==0){
            holder.timeOnRight.visibility = GONE
            holder.timeOnLeft.visibility= VISIBLE
            holder.timeOnLeft.text= time

            holder.nameOnLeft.visibility= GONE
            holder.nameOnRight.visibility= VISIBLE
            holder.nameOnRight.text = name

            holder.venueOnLeft.visibility= GONE
            holder.venueOnRight.visibility= VISIBLE
            holder.venueOnRight.text= venue
        }
        else{
            holder.nameOnRight.visibility= GONE
            holder.nameOnLeft.visibility = VISIBLE
            holder.nameOnLeft.text= name

            holder.timeOnLeft.visibility= GONE
            holder.timeOnRight.visibility= VISIBLE
            holder.timeOnRight.text= time

            holder.venueOnLeft.visibility= VISIBLE
            holder.venueOnRight.visibility= GONE
            holder.venueOnLeft.text= venue
        }
        holder.itemView.setOnClickListener {
            showCustomDescriptionDialog(currentItem, holder.itemView.context)
        }
    }

    private fun showCustomDescriptionDialog(item: ScheduleModel, context: Context) {
        val dialog = BottomSheetDialog(context)
        dialog.setContentView(R.layout.schedule_information_card)
        val eventImage = dialog.findViewById<ImageView>(R.id.img)
        val descriptionTextView = dialog.findViewById<TextView>(R.id.tvDescription)
        val closeButton = dialog.findViewById<AppCompatImageButton>(R.id.close_btn)

        if (descriptionTextView != null) {
            descriptionTextView.text = item.eventDescription
        }
        if (eventImage != null) {
            Glide.with(context).load(item.eventPosterUrl)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .into(eventImage)
        }
        closeButton?.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }
}