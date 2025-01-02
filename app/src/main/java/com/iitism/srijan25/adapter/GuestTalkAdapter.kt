package com.iitism.srijan25.adapter

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.iitism.srijan25.Data.GuestTalkData
import com.iitism.srijan25.R

class GuestTalkAdapter(
    private val dataList: List<GuestTalkData>,
    private val context: Context
) : RecyclerView.Adapter<GuestTalkAdapter.LecturesViewHolder>() {

    inner class LecturesViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LecturesViewHolder {
        val viewHolder =
            LayoutInflater.from(parent.context).inflate(R.layout.guest_talk_card_view, parent, false)
        return LecturesViewHolder(viewHolder)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: LecturesViewHolder, position: Int) {
        val item = dataList[position] // Get the GuestTalkData item

        val imageView = holder.itemView.findViewById<ImageView>(R.id.guest_img)
        val titleTextView = holder.itemView.findViewById<TextView>(R.id.tvGuestName)
        val viewMoreButton = holder.itemView.findViewById<Button>(R.id.btn_viewmore)

        // Load the image using Glide
        Glide.with(holder.itemView.context).load(item.guestImage)
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .into(imageView)

        // Set the title
        titleTextView.text = item.guestName

        // Set the click listener for the "View More" button
        viewMoreButton.setOnClickListener {
            showCustomDescriptionDialog(item)
        }
    }

    // Function to show a custom dialog with the description
    private fun showCustomDescriptionDialog(item: GuestTalkData) {
        val dialog = Dialog(context)
        dialog.setContentView(R.layout.guest_talk_desctiption_dialog)
        dialog.window?.setBackgroundDrawableResource(R.drawable.description_rounded)
        // Find views in the dialog
        val titleTextView = dialog.findViewById<TextView>(R.id.tv_dialog_title)
        val descriptionTextView = dialog.findViewById<TextView>(R.id.tv_dialog_description)
        val closeButton = dialog.findViewById<Button>(R.id.btn_close)

        // Set title and description in the dialog
        titleTextView.text = item.guestName
        descriptionTextView.text = item.briefDescription

        // Set the close button's click listener
        closeButton.setOnClickListener {
            dialog.dismiss()
        }

        // Show the dialog
        dialog.show()
    }
}
