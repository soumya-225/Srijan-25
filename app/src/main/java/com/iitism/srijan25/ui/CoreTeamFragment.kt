package com.iitism.srijan25.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.iitism.srijan25.adapter.CoreTeamAdapter
import com.iitism.srijan25.databinding.FragmentCoreTeamBinding
import com.iitism.srijan25.viewModel.CoreTeamViewModel

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

        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        binding.rvCoreTeam.layoutManager = GridLayoutManager(context, 2)
        binding.rvCoreTeam.setHasFixedSize(true)

        viewModel = CoreTeamViewModel(application)
        viewModel.getCoreTeamList()

        val itemAdapter = CoreTeamAdapter(viewModel.coreTeamList)
        binding.rvCoreTeam.adapter = itemAdapter
    }
}