package com.iitism.srijan25.ui

import android.app.Application
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.iitism.srijan25.MyApplication
import com.iitism.srijan25.viewModel.CoreTeamViewModel
import com.iitism.srijan25.adapter.CoreTeamAdapter
import com.iitism.srijan25.databinding.FragmentCoreTeamBinding

class CoreTeamFragment : Fragment() {
    private lateinit var viewModel: CoreTeamViewModel
    private lateinit var binding: FragmentCoreTeamBinding
    private lateinit var application: Context

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCoreTeamBinding.inflate(layoutInflater, container, false)
        application = requireContext()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvCoreTeam.layoutManager = LinearLayoutManager(context)
        binding.rvCoreTeam.setHasFixedSize(true)

        viewModel = CoreTeamViewModel(application)
        viewModel.getCoreTeamList()

        val itemAdapter = CoreTeamAdapter(viewModel.coreTeamList)
        //val itemAdapter = CoreTeamAdapter(viewModel.coreTeam)
        binding.rvCoreTeam.adapter = itemAdapter
    }
}