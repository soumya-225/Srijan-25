package com.iitism.srijan25.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.iitism.srijan25.adapter.AnnouncementsRVAdapter
import com.iitism.srijan25.databinding.FragmentAnnouncementBinding
import com.iitism.srijan25.model.Announcement
import com.iitism.srijan25.data.remote.AnnouncementRetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AnnouncementsFragment : Fragment(), AnnouncementsRVAdapter.OnTimestampUpdateListener {
    private lateinit var binding: FragmentAnnouncementBinding
    private lateinit var announcementsAdapter: AnnouncementsRVAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentAnnouncementBinding.inflate(inflater, container, false)
        binding.progressBar.visibility = View.VISIBLE
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        announcementsAdapter = AnnouncementsRVAdapter(this)
        binding.rvAnnouncements.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = announcementsAdapter
        }
        binding.swipeRefreshLayout.setOnRefreshListener {
            fetchAnnouncements()
            binding.swipeRefreshLayout.isRefreshing = false
        }
        fetchAnnouncements()
    }

    private fun fetchAnnouncements() {
        val call = AnnouncementRetrofitInstance.announcementService.getAnnouncements()

        call.enqueue(object : Callback<List<Announcement>> {
            override fun onResponse(
                call: Call<List<Announcement>>, response: Response<List<Announcement>>
            ) {
                if (response.isSuccessful) {
                    val announcements = response.body()
                    announcements?.let {
                        announcementsAdapter.refreshAnnouncements(it)
                        announcementsAdapter.updateTimestamps()
                        binding.progressBar.visibility = View.GONE
                    }
                } else {
                    Toast.makeText(context, "Failed to load data...", Toast.LENGTH_SHORT).show()
                    binding.progressBar.visibility = View.GONE
                }
            }

            override fun onFailure(call: Call<List<Announcement>>, t: Throwable) {
                Log.e("FetchAnnouncements", "Network request failed", t)
                binding.progressBar.visibility = View.GONE
            }
        })
    }

    override fun onUpdateTimestamp(position: Int, timeAgo: String) {
        if (!this::binding.isInitialized)
            return
        binding.rvAnnouncements.findViewHolderForAdapterPosition(position)?.let { viewHolder ->
            (viewHolder as? AnnouncementsRVAdapter.AnnouncementsViewHolder)?.tvTime?.text = timeAgo
        }
    }
}