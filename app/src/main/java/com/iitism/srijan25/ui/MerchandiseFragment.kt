package com.iitism.srijan25.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.google.android.material.appbar.MaterialToolbar
import com.iitism.srijan25.R


class MerchandiseFragment : Fragment() {
    private var imagePager: ViewPager2? = null

    var imageList = listOf(
        R.drawable.tshirt25,
        R.drawable.hoodie25,
    )

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_merchandise, container, false)
//        imagePager = view.findViewById(R.id.view_pager_carousel_merch)
//        val adapter = ImagePagerAdapter(imageList)
//        imagePager?.adapter = adapter
//        startImageSliderTimer()
        val toolbar = view.findViewById<MaterialToolbar>(R.id.toolbar)

        toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
        val btnPlace = view.findViewById<Button>(R.id.PlaceOrder)
        btnPlace.setOnClickListener {
            openGoogleForm()
        }
        return view
    }

    private fun openGoogleForm() {
        val formUrl = "https://forms.gle/Lh8Divrwx2TbXpbQ6"
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(formUrl)
        startActivity(intent)
    }

    private fun startImageSliderTimer() {
        val handler = Handler(Looper.getMainLooper())
        val updateImageSliderTask = object : Runnable {
            override fun run() {
                val currentPage = imagePager?.currentItem
                var nextPage = currentPage?.plus(1)
                if (nextPage != null) {
                    if (nextPage >= imageList.size) {
                        nextPage = 0
                    }
                }
                if (nextPage != null) {
                    imagePager?.currentItem = nextPage
                }
                handler.postDelayed(this, 4000)
            }
        }
        handler.postDelayed(updateImageSliderTask, 4000)
    }

    class ImagePagerAdapter(private val imageList: List<Int>) :
        RecyclerView.Adapter<ImagePagerAdapter.ImagePagerViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagePagerViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_image_pager, parent, false)
            return ImagePagerViewHolder(view)
        }

        override fun onBindViewHolder(holder: ImagePagerViewHolder, position: Int) {
            val imageRes = imageList[position]
            holder.bind(imageRes)
        }

        override fun getItemCount(): Int {
            return imageList.size
        }

        inner class ImagePagerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            private val imageView: ImageView = itemView.findViewById(R.id.pageImage)

            fun bind(imageRes: Int) {
                Glide.with(itemView.context)
                    .load(imageRes)
                    .into(imageView)
            }
        }
    }

}

