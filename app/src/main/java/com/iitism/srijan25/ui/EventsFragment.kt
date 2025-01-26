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
    private lateinit var category: String

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
            category="1"
            navigateAction(category)
        }

        binding.dance.setOnClickListener {
            category="2"
            navigateAction(category)
        }

        binding.literary.setOnClickListener {
            category="5"
            navigateAction(category)
        }

        binding.music.setOnClickListener {
            category="7"
            navigateAction(category)
        }

        binding.comedy.setOnClickListener {
            category="4"
            navigateAction(category)
        }

        binding.trivia.setOnClickListener {
            category="3"
            navigateAction(category)
        }

        binding.drama.setOnClickListener {
            category="6"
            navigateAction(category)
        }

        binding.fashion.setOnClickListener {
            category="8"
            navigateAction(category)
        }

        binding.finearts.setOnClickListener {
            category="9"
            navigateAction(category)
        }

        return binding.root
    }

    private fun navigateAction(category: String){
        val action =EventsFragmentDirections.actionEventsFragmentToEventsListFragment(category)
        navController.navigate(action)
    }
}