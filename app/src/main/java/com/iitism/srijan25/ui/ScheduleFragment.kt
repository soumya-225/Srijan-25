package com.iitism.srijan25.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.iitism.srijan25.adapter.ScheduleAdapter
import com.iitism.srijan25.databinding.FragmentScheduleBinding
import com.iitism.srijan25.model.EventItem
import com.iitism.srijan25.model.X1
import java.text.SimpleDateFormat
import java.util.Locale

class ScheduleFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var scheduleAdapter: ScheduleAdapter
    private lateinit var eventList: List<X1>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentScheduleBinding.inflate(inflater, container, false)
        recyclerView = binding.recyclerView

        eventList = loadEventDetails()

        scheduleAdapter = ScheduleAdapter(eventList)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = scheduleAdapter

        return binding.root
    }

    private fun loadEventDetails(): List<X1> {
        val json = requireContext().assets.open("event_details.json").bufferedReader()
            .use { it.readText() }
        val gson = Gson()
        val eventType = object : TypeToken<List<EventItem>>() {}.type
        val eventList: List<EventItem> = gson.fromJson(json, eventType)

        val flattenedList = eventList.flatMap { item ->
            (item.`1` ?: emptyList()) + (item.`2` ?: emptyList()) + (item.`3` ?: emptyList()) +
                    (item.`4` ?: emptyList()) + (item.`5` ?: emptyList()) + (item.`6`
                ?: emptyList()) +
                    (item.`7` ?: emptyList()) + (item.`8` ?: emptyList()) + (item.`9`
                ?: emptyList())
        }

        return flattenedList.sortedBy { event ->
            convertTimeToMillis(event.timeFormatted)
        }
    }

    private fun convertTimeToMillis(timeFormatted: String): Long {
        val dateFormat = SimpleDateFormat(
            "dd-MMM-yyyy hh:mma",
            Locale.getDefault()
        )
        return try {
            val date = dateFormat.parse(timeFormatted)
            date?.time ?: Long.MAX_VALUE
        } catch (e: Exception) {
            e.printStackTrace()
            Long.MAX_VALUE
        }
    }
}
