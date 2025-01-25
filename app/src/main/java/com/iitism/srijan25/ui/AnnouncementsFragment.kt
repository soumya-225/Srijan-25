package com.iitism.srijan25.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.iitism.srijan25.adapter.AnnouncementAdapter
import com.iitism.srijan25.databinding.FragmentAnnouncementBinding
import com.iitism.srijan25.viewModel.AnnouncementViewModel

class AnnouncementsFragment : Fragment(), AnnouncementAdapter.OnTimestampUpdateListener {
    private lateinit var binding: FragmentAnnouncementBinding
    private lateinit var announcementsAdapter: AnnouncementAdapter
    private lateinit var viewModel: AnnouncementViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentAnnouncementBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[AnnouncementViewModel::class.java]
        binding.progressBar.visibility = View.VISIBLE
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        announcementsAdapter = AnnouncementAdapter(this)
        binding.rvAnnouncements.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = announcementsAdapter
        }
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.fetchAnnouncements()
            binding.swipeRefreshLayout.isRefreshing = false
        }

        viewModel.announcements.observe(viewLifecycleOwner) { announcements ->
            announcements?.let {
                announcementsAdapter.refreshAnnouncements(it)
                announcementsAdapter.updateTimestamps()
                binding.progressBar.visibility = View.GONE
            }
        }

        viewModel.fetchAnnouncements()
        binding.progressBar.visibility = View.VISIBLE

        viewModel.fetchError.observe(viewLifecycleOwner) { errorMessage ->
            errorMessage?.let {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
                binding.progressBar.visibility = View.GONE
            }
        }
    }

    override fun onUpdateTimestamp(position: Int, timeAgo: String) {
        if (!this::binding.isInitialized)
            return
        binding.rvAnnouncements.findViewHolderForAdapterPosition(position)?.let { viewHolder ->
            (viewHolder as? AnnouncementAdapter.AnnouncementsViewHolder)?.tvTime?.text = timeAgo
        }
    }
}