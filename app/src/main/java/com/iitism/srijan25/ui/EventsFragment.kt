package com.iitism.srijan25.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.iitism.srijan25.R
import com.iitism.srijan25.adapter.EventsTabAdapter

class EventsFragment : Fragment() {

    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout
    private lateinit var adapter: EventsTabAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_events, container, false)

        viewPager = view.findViewById(R.id.viewPager)
        tabLayout = view.findViewById(R.id.tabLayout)
        setupViewPager()
        return view
    }

    private fun setupViewPager() {
        adapter = EventsTabAdapter(childFragmentManager, lifecycle)
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            val customTabView = LayoutInflater.from(context).inflate(R.layout.custom_tab_layout, tabLayout, false)
            val tabText = customTabView.findViewById<TextView>(R.id.tab_text)

            when (position) {
                0 -> tabText.text = requireContext().getString(R.string.club_events)
                1 -> tabText.text = requireContext().getString(R.string.dept_events)
            }
            tab.customView = customTabView
        }.attach()

        handleTabSelection()
    }

    private fun handleTabSelection() {
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                tab.customView?.isSelected = true
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                tab.customView?.isSelected = false
            }

            override fun onTabReselected(tab: TabLayout.Tab) {
                // No special behavior needed for reselected tabs
            }
        })
    }
}
