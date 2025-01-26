package com.iitism.srijan25.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.iitism.srijan25.databinding.FragmentEventDetailsBinding
import com.iitism.srijan25.model.EventNew

class EventDetailsFragment : Fragment() {

    private lateinit var event: EventNew

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentEventDetailsBinding = FragmentEventDetailsBinding.inflate(inflater, container, false)

        event=navArgs<EventDetailsFragmentArgs>().value.event

        binding.appToolbar.title = event.name
        binding.appToolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        binding.tvEventName.text = event.name
        binding.tvEventDescription.text = event.description
        binding.tvPrizePool.text="Prize Pool: Rs. ${event.prizePool}"
        binding.tvEventTime.text=event.timeFormatted
        binding.tvEventVenue.text="Venue: ${event.venue}"

        binding.btnRegister.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(event.registerLink))
            requireContext().startActivity(intent)
        }

        binding.btnRulebook.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(event.rulebook))
            requireContext().startActivity(intent)
        }

        return binding.root
    }
}
