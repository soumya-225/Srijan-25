package com.iitism.srijan25.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.iitism.srijan25.R
import com.iitism.srijan25.ui.GalleryFragmentDirections

class GalleryAdapter(
    private val imageUrls: List<String>,
    private val navController: NavController
) : RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder>() {

    inner class GalleryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_gallery, parent, false)
        return GalleryViewHolder(view)
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        val imageUrl = imageUrls[position]
        Glide.with(holder.imageView.context)
            .load(imageUrl)
            .placeholder(R.drawable.progress_animation)
            .error(R.drawable.srijan25_logo_blackbg)
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .into(holder.imageView)

        holder.imageView.setOnClickListener {
            val action = GalleryFragmentDirections.actionGalleryFragmentToImageViewFragment(imageUrl)
            navController.navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return imageUrls.size
    }
}
