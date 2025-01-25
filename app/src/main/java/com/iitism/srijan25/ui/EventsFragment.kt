package com.iitism.srijan25.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.iitism.srijan25.R
import com.iitism.srijan25.databinding.FragmentClubEventsBinding

class EventsFragment : Fragment() {
    private lateinit var binding: FragmentClubEventsBinding
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentClubEventsBinding.inflate(inflater, container, false)
        navController = findNavController()

        binding.toolbar.setNavigationOnClickListener {
            navController.navigateUp()
        }

        binding.cinema.setOnClickListener {
            navController.navigate(R.id.action_eventsFragment_to_eventsListFragment)
        }

        binding.dance.setOnClickListener {
            navController.navigate(R.id.action_eventsFragment_to_eventsListFragment)
        }

        binding.literary.setOnClickListener {
            navController.navigate(R.id.action_eventsFragment_to_eventsListFragment)
        }

        binding.music.setOnClickListener {
            navController.navigate(R.id.action_eventsFragment_to_eventsListFragment)
        }

        binding.comedy.setOnClickListener {
            navController.navigate(R.id.action_eventsFragment_to_eventsListFragment)
        }

        binding.trivia.setOnClickListener {
            navController.navigate(R.id.action_eventsFragment_to_eventsListFragment)
        }

        binding.drama.setOnClickListener {
            navController.navigate(R.id.action_eventsFragment_to_eventsListFragment)
        }

        binding.culinary.setOnClickListener {
            navController.navigate(R.id.action_eventsFragment_to_eventsListFragment)
        }

        binding.fashion.setOnClickListener {
            navController.navigate(R.id.action_eventsFragment_to_eventsListFragment)
        }

        binding.finearts.setOnClickListener {
            navController.navigate(R.id.action_eventsFragment_to_eventsListFragment)
        }

        return binding.root
    }
}