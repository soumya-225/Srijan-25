package com.iitism.srijan25.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.iitism.srijan25.R
import com.iitism.srijan25.adapter.GuestTalkAdapter
import com.iitism.srijan25.databinding.FragmentGuestTalkBinding
import com.iitism.srijan25.model.GuestTalkData
import java.io.InputStream

class GuestTalkFragment : Fragment() {
    private lateinit var binding: FragmentGuestTalkBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentGuestTalkBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val guestTalkList = mutableListOf<GuestTalkData>()
        val inputStream: InputStream = requireContext().assets.open("guest_lectures.json")
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()

        val json = String(buffer, Charsets.UTF_8)
        val gson = Gson()

        val guestTalks = gson.fromJson(json, Array<GuestTalkData>::class.java)
        guestTalkList.addAll(guestTalks)

        val recyclerView = view.findViewById<RecyclerView>(R.id.rvGuestTalk)
        recyclerView.adapter = GuestTalkAdapter(guestTalkList, requireContext())
        recyclerView.layoutManager = LinearLayoutManager(context)
    }
}