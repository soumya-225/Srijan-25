package com.iitism.srijan25.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.iitism.srijan25.R
import com.iitism.srijan25.model.Announcement
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

class AnnouncementAdapter(private val onTimestampUpdateListener: OnTimestampUpdateListener) :
    RecyclerView.Adapter<AnnouncementAdapter.AnnouncementsViewHolder>() {

    interface OnTimestampUpdateListener {
        fun onUpdateTimestamp(position: Int, timeAgo: String)
    }

    private var announcements: List<Announcement> = emptyList()

    inner class AnnouncementsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        val tvBody: TextView = itemView.findViewById(R.id.tvBody)
        val tvTime: TextView = itemView.findViewById(R.id.tvTime)
    }

    fun refreshAnnouncements(newAnnouncements: List<Announcement>) {
        announcements = newAnnouncements
        announcements = newAnnouncements.sortedByDescending { it.timestamp }
        notifyDataSetChanged()
    }

    fun updateTimestamps() {
        for (i in announcements.indices) {
            val currentAnnouncement = announcements[i]
            val localTimestamp = convertMillisToLocal(currentAnnouncement.timestamp)
            val timeAgo = getTimeAgo(localTimestamp)

            onTimestampUpdateListener.onUpdateTimestamp(i, timeAgo)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnnouncementsViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_rv_announcement, parent, false)
        return AnnouncementsViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return announcements.size
    }

    override fun onBindViewHolder(holder: AnnouncementsViewHolder, position: Int) {
        val currentAnnouncement = announcements[position]
        holder.tvTitle.text = currentAnnouncement.title
        holder.tvBody.text = currentAnnouncement.body

        val localTimestamp = convertMillisToLocal(currentAnnouncement.timestamp)
        val timeAgo = getTimeAgo(localTimestamp)
        holder.tvTime.text = timeAgo
    }

    private fun convertMillisToLocal(milliseconds: Long): String {
        val localDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        localDateFormat.timeZone = TimeZone.getDefault()
        return localDateFormat.format(milliseconds)
    }

    private fun getTimeAgo(localTimestamp: String): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val localDate = try {
            dateFormat.parse(localTimestamp)
        } catch (e: Exception) {
            return ""
        }

        val now = System.currentTimeMillis()
        val timeDifference = now - localDate.time

        val seconds = timeDifference / 1000
        val minutes = seconds / 60
        val hours = minutes / 60
        val days = hours / 24

        return when {
            days.toInt() == 1 -> "1 day ago"
            days > 1 -> "${days.toInt()} days ago"
            hours.toInt() == 1 -> "1 hour ago"
            hours > 1 -> "${hours.toInt()} hours ago"
            minutes.toInt() == 1 -> "1 minute ago"
            minutes > 1 -> "${minutes.toInt()} min ago"
            else -> "Now"
        }
    }
}