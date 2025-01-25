package com.iitism.srijan25.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.iitism.srijan25.R
import com.iitism.srijan25.databinding.FragmentHomeBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.Timer
import java.util.TimerTask

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
    ): View {
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
            navController.navigate(R.id.action_mainFragment_to_eventsFragment)
        }
        merchBtn.setOnClickListener {
            navController.navigate(R.id.action_mainFragment_to_merchandiseFragment)
        }
        galleryBtn.setOnClickListener {
            navController.navigate(R.id.action_mainFragment_to_galleryFragment)
        }
        coreTeamBtn.setOnClickListener {
            navController.navigate(R.id.action_mainFragment_to_coreTeamFragment)
        }
        caBtn.setOnClickListener {
            navController.navigate(R.id.action_mainFragment_to_aboutFragment)
        }
        sponsorsBtn.setOnClickListener {
            navController.navigate(R.id.action_mainFragment_to_sponsorsFragment)
        }
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
                        binding.txtDay.text = "" + String.format(Locale.getDefault(), "%02d", days)
                        binding.txtHour.text =
                            "" + String.format(Locale.getDefault(), "%02d", hours)
                        binding.txtMinute.text =
                            "" + String.format(Locale.getDefault(), "%02d", minutes)
                        binding.txtSecond.text =
                            "" + String.format(Locale.getDefault(), "%02d", seconds)
                    } else {
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


    fun countDownConcettoEnd() {
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
                        binding.txtDay.text = "" + String.format(Locale.getDefault(), "%02d", days)
                        binding.txtHour.text =
                            "" + String.format(Locale.getDefault(), "%02d", hours)
                        binding.txtMinute.text =
                            "" + String.format(Locale.getDefault(), "%02d", minutes)
                        binding.txtSecond.text =
                            "" + String.format(Locale.getDefault(), "%02d", seconds)
                    } else {
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
        handler.postDelayed(runnable, 1 * 1000)
    }

    private fun startAutoScroll() {
        timer = Timer()
        timer?.schedule(object : TimerTask() {
            override fun run() {
                handler.post {
                    if (currentPage == totalPages - 1)
                        currentPage = 0
                    else
                        currentPage++
                }
            }
        }, 0, autoScrollDelay)
    }
}