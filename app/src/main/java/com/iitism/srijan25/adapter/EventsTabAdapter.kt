package com.iitism.srijan25.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.iitism.srijan25.ui.ClubEventsFragment
import com.iitism.srijan25.ui.DepartmentalEventsFragment

class EventsTabAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return 1
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ClubEventsFragment()
//            1 -> DepartmentalEventsFragment()
            else -> ClubEventsFragment()
        }
    }
}
