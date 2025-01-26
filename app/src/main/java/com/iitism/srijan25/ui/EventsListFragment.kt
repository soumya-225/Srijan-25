package com.iitism.srijan25.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.iitism.srijan25.adapter.EventsAdapter
import com.iitism.srijan25.databinding.FragmentEventsListBinding
import com.iitism.srijan25.viewModel.EventViewModel

class EventsListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var eventsAdapter: EventsAdapter
    private lateinit var viewModel: EventViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentEventsListBinding =
            FragmentEventsListBinding.inflate(inflater, container, false)

        val category = navArgs<EventsListFragmentArgs>().value.category

        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)

        binding.appToolbar.title = getCategory(category)

        binding.appToolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
        viewModel = ViewModelProvider(this)[EventViewModel::class.java]

        viewModel.eventsLiveData.observe(viewLifecycleOwner) { categories ->
            val firstCategory = categories.firstOrNull()
            if (firstCategory != null) {
                eventsAdapter = EventsAdapter(firstCategory.events){ event ->
                        val action = EventsListFragmentDirections.actionEventsListFragmentToEventDetailsFragment(event)
                        findNavController().navigate(action)
                }
                recyclerView.adapter = eventsAdapter
            }
        }

        viewModel.loadEvents(category)

        return binding.root
    }

    private fun getCategory(category: String): String {
        return when (category) {
            "1" -> "Cinema Events"
            "2" -> "Dance Events"
            "3" -> "Quiz Events"
            "4" -> "Comdey Events"
            "5" -> "Literary Events"
            "6" -> "Drama Events"
            "7" -> "Music Events"
            "8" -> "Fashion Events"
            "9" -> "Art Events"
            else -> "Events"
        }
    }
}
