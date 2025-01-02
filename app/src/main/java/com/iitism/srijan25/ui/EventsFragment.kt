package com.iitism.srijan25.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
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
        // Initialize the adapter and set it to ViewPager
        adapter = EventsTabAdapter(childFragmentManager, lifecycle)
        viewPager.adapter = adapter

        // Attach TabLayout with ViewPager and set custom views for tabs
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            val customTabView = LayoutInflater.from(context).inflate(R.layout.custom_tab_layout, null)
            val tabText = customTabView.findViewById<TextView>(R.id.tab_text)

            when (position) {
                0 -> {
                    tabText.text = "Club Events"
                }
                1 -> {
                    tabText.text = "Departmental Events"
                }
            }
            tab.customView = customTabView
        }.attach()

        // Set tab state listener to change appearance when selected or unselected
        handleTabSelection()
    }

    private fun handleTabSelection() {
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                // Set tab as selected (will change background to filled)
                tab.customView?.isSelected = true
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                // Set tab as unselected (will change background to outlined)
                tab.customView?.isSelected = false
            }

            override fun onTabReselected(tab: TabLayout.Tab) {
                // No special behavior needed for reselected tabs
            }
        })
    }
}
