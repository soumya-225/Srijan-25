package com.iitism.srijan25.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.iitism.srijan25.R
import com.iitism.srijan25.adapter.AdapterViewPager
import com.iitism.srijan25.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private lateinit var bottomNav: BottomNavigationView
    private lateinit var pagerMain: ViewPager2
    private var fragmentArrList: ArrayList<Fragment> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        bottomNav = binding.bottomNavigationView
        pagerMain = binding.pagerMain

        fragmentArrList.add(HomeFragment())
        fragmentArrList.add(EventsFragment())
        fragmentArrList.add(AnnouncementsFragment())

        val adapterViewPager = AdapterViewPager(requireActivity(), fragmentArrList)
        pagerMain.adapter = adapterViewPager

        // Sync the ViewPager2 with BottomNavigationView
        pagerMain.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                when (position) {
                    0 -> bottomNav.selectedItemId = R.id.home
                    1 -> bottomNav.selectedItemId = R.id.events
                    2 -> bottomNav.selectedItemId = R.id.updates
                }
            }
        })

        // Handle BottomNavigationView clicks
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> pagerMain.currentItem = 0
                R.id.events -> pagerMain.currentItem = 1
                R.id.updates -> pagerMain.currentItem = 2
                else -> false
            }
            true
        }

        return binding.root
    }
}
