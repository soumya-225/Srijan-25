package com.iitism.srijan25.ui

import android.annotation.SuppressLint
import android.content.res.Resources
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.iitism.srijan25.R
import com.iitism.srijan25.R.id.action_homeFragment_to_GalleryFragment
import com.iitism.srijan25.R.id.action_homeFragment_to_main_stage
import com.iitism.srijan25.adapter.HomeCarouselAdapter
import com.iitism.srijan25.databinding.FragmentHomeBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.Timer
import java.util.TimerTask
import kotlin.math.abs

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val autoScrollDelay: Long = 3000
    private val totalPages = 6
    private var currentPage = 0
    private var timer: Timer? = null
    private val handler = Handler(Looper.getMainLooper())
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View
    {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        navController = findNavController()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        countDownConcettoStart()
        startAutoScroll()

        val eventsBtn = binding.ivEvents
        val merchBtn = binding.ivMerch
        val sponsorsBtn = binding.ivSponsors
        val caBtn = binding.caIcon
        val coreTeamBtn = binding.ivCoreTeam
        val galleryBtn = binding.galleryIcon

        eventsBtn.setOnClickListener {
           navController.navigate(R.id.action_mainFragment_to_eventsFragment2)
        }
        merchBtn.setOnClickListener {
            navController.navigate(R.id.action_mainFragment_to_merchandiseFragment2)
        }
        galleryBtn.setOnClickListener {
            navController.navigate(R.id.action_mainFragment_to_galleryFragment)
        }
        coreTeamBtn.setOnClickListener {
            navController.navigate(R.id.action_mainFragment_to_coreTeamFragment2)
        }
        caBtn.setOnClickListener {
            navController.navigate(R.id.action_mainFragment_to_aboutFragment)
        }
        sponsorsBtn.setOnClickListener {
            navController.navigate(R.id.action_mainFragment_to_sponsorsFragment2)
        }

        val images = arrayOf(
            "https://res.cloudinary.com/dc3prss4c/image/upload/v1729000509/ubg727ooxhcayxdkhnsp.png",
           "https://res.cloudinary.com/dnywj3xrl/image/upload/v1725973132/IMG-20240910-WA0054_p41az0.jpg",
            "https://res.cloudinary.com/dnywj3xrl/image/upload/v1725973132/IMG-20240910-WA0053_bijxkg.jpg",
            "https://res.cloudinary.com/dnywj3xrl/image/upload/v1725973121/IMG-20240910-WA0055_ue4b8r.jpg",
           "https://res.cloudinary.com/dnywj3xrl/image/upload/v1725973121/IMG-20240910-WA0046_b92t9n.jpg",
            "https://res.cloudinary.com/dnywj3xrl/image/upload/v1725973121/IMG-20240910-WA0051_gfdou9.jpg",
            "https://res.cloudinary.com/dnywj3xrl/image/upload/v1725973562/IMG-20240910-WA0060_nsplld.jpg"
        )

        //binding.viewPagerCarousel.adapter = HomeCarouselAdapter(images)

//        val compositePageTransformer = CompositePageTransformer()
//        compositePageTransformer.addTransformer(MarginPageTransformer((40 * Resources.getSystem().displayMetrics.density).toInt()))
//        compositePageTransformer.addTransformer { page, position ->
//            val r = 1 - abs(position)
//            page.scaleY = (0.80f + r * 0.20f)
//        }
        //binding.viewPagerCarousel.setPageTransformer(compositePageTransformer)

        //addDotsIndicator(images.size)
//        binding.viewPagerCarousel.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback()
//        {
//            override fun onPageSelected(position: Int) {
//                super.onPageSelected(position)
//                updateDots(position)
//            }
//        })
//
//        binding.viewPagerCarousel.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
//            override fun onPageSelected(position: Int) {
//                currentPage = position
//            }
//        })
//        binding.galleryIcon.setOnClickListener {
//            val nav=findNavController()
//            nav.navigate(action_homeFragment_to_GalleryFragment)
//        }
//        binding.mainstageIcon.setOnClickListener {
//            findNavController().navigate(action_homeFragment_to_main_stage)
//        }
    }

    private fun countDownConcettoStart() {
        @Suppress("DEPRECATION") val handler = Handler()
        val runnable = object : Runnable {
            @SuppressLint("SetTextI18n", "SimpleDateFormat")
            override fun run() {
                handler.postDelayed(this, 1000)
                try {
                    val currentDate = Date()
                    val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                    val futureDate: Date = dateFormat.parse("2025-01-31 00:00:00")!!
                    if (!currentDate.after(futureDate)) {
                        var diff: Long = (futureDate.time - currentDate.time)
                        val days = diff / (24 * 60 * 60 * 1000)
                        diff -= days * (24 * 60 * 60 * 1000)
                        val hours = diff / (60 * 60 * 1000)
                        diff -= hours * (60 * 60 * 1000)
                        val minutes = diff / (60 * 1000)
                        diff -= minutes * (60 * 1000)
                        val seconds = diff / 1000
                        binding.txtDay.text = "" + String.format(Locale.getDefault(),"%02d", days)
                        binding.txtHour.text = "" + String.format(Locale.getDefault(),"%02d", hours)
                        binding.txtMinute.text = "" + String.format(Locale.getDefault(),"%02d", minutes)
                        binding.txtSecond.text = "" + String.format(Locale.getDefault(),"%02d",seconds)
                    }
                    else {
                        countDownConcettoEnd()
                        binding.textCounterDown.text = "Concetto'24 is Live"
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        handler.postDelayed(runnable, 1 * 1000)

    }



    fun countDownConcettoEnd(){
        @Suppress("DEPRECATION") val handler = Handler()
        val runnable = object : Runnable {
            @SuppressLint("SetTextI18n", "SimpleDateFormat")
            override fun run() {
                handler.postDelayed(this, 1000)
                try {
                    val currentDate = Date()
                    val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                    val futureDate: Date = dateFormat.parse("2025-02-02 00:00:00")!!
                    if (!currentDate.after(futureDate)) {

                        var diff: Long = (futureDate.time - currentDate.time)
                        val days = diff / (24 * 60 * 60 * 1000)
                        diff -= days * (24 * 60 * 60 * 1000)
                        val hours = diff / (60 * 60 * 1000)
                        diff -= hours * (60 * 60 * 1000)
                        val minutes = diff / (60 * 1000)
                        diff -= minutes * (60 * 1000)
                        val seconds = diff / 1000
                        binding.txtDay.text = "" + String.format(Locale.getDefault(),"%02d", days)
                        binding.txtHour.text = "" + String.format(Locale.getDefault(),"%02d", hours)
                        binding.txtMinute.text = "" + String.format(Locale.getDefault(),"%02d", minutes)
                        binding.txtSecond.text = "" + String.format(Locale.getDefault(),"%02d",seconds)
                    }
                    else {
                        binding.textCounterDown.text = "Concetto'24 is Over"

                        binding.txtDay.visibility = View.INVISIBLE
                        binding.txtHour.visibility = View.INVISIBLE
                        binding.txtMinute.visibility = View.INVISIBLE
                        binding.txtSecond.visibility = View.INVISIBLE

                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        handler.postDelayed(runnable,  1 * 1000)
    }

    private fun addDotsIndicator(size: Int) {
        val dots = arrayOfNulls<ImageView>(size)
        for (i in 0 until size) {
            dots[i] = ImageView(requireContext())
            dots[i]?.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.indicator_inactive))
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(8, 0, 8, 0)
            binding.dotLayout.addView(dots[i], params)
        }
        dots[0]?.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.indicator_active))
    }

    private fun updateDots(position: Int) {
        val childCount = binding.dotLayout.childCount
        for (i in 0 until childCount) {
            val dot = binding.dotLayout.getChildAt(i) as ImageView
            val drawableId =
                if (i == position) R.drawable.indicator_active else R.drawable.indicator_inactive
            dot.setImageDrawable(ContextCompat.getDrawable(requireContext(), drawableId))
        }
    }

    private fun startAutoScroll() {
        timer = Timer()
        timer?.schedule(object : TimerTask() {
            override fun run() {
                handler.post {
                    if (currentPage == totalPages - 1) {
                        currentPage = 0
                    } else {
                        currentPage++
                    }
                    //binding.viewPagerCarousel.setCurrentItem(currentPage, true)
                }
            }
        }, 0, autoScrollDelay)
    }

    private fun stopAutoScroll() {
        timer?.cancel()
        timer?.purge()
        timer = null
    }

    private fun restartAutoScroll() {
        stopAutoScroll()
        handler.postDelayed({ startAutoScroll() }, autoScrollDelay)
    }
}