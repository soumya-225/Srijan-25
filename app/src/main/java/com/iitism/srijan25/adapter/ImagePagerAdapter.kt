package com.iitism.srijan25.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.iitism.srijan25.R

class ImagePagerAdapter (
    private val imageUrls: List<String>,
    private val fragment: Fragment
) : RecyclerView.Adapter<ImagePagerAdapter.ImageViewHolder>() {

    // ViewHolder class to hold each page's view
    class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.pageImage)
    }

    // Inflate the layout for each page (item_image_pager.xml)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_image_pager, parent, false)
        return ImageViewHolder(view)
    }

    // Bind the image to each ViewHolder
    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val imageUrl = imageUrls[position]

        // Use Glide to load the image from the URL into the ImageView
        Glide.with(fragment)
            .load(imageUrl)
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .into(holder.imageView)
    }

    // Return the number of items
    override fun getItemCount(): Int {
        return imageUrls.size
    }
}
