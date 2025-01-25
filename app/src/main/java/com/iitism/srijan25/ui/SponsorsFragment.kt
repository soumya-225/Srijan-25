package com.iitism.srijan25.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.iitism.srijan25.adapter.SponsorRVAdapter
import com.iitism.srijan25.databinding.FragmentSponsorsBinding
import com.iitism.srijan25.viewModel.SponsorViewModel

class SponsorsFragment : Fragment() {
    private lateinit var binding: FragmentSponsorsBinding
    private lateinit var viewModel: SponsorViewModel
    private lateinit var adapter: SponsorRVAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSponsorsBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(
            requireActivity(),
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        )[SponsorViewModel::class.java]

        adapter= SponsorRVAdapter(emptyList())


        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        binding.rvSponsors.layoutManager = GridLayoutManager(context, 2)
        binding.rvSponsors.setHasFixedSize(true)

        binding.rvSponsors.adapter = adapter

        viewModel.sponsorData.observe(viewLifecycleOwner) { data ->
            adapter.setData(data)
        }

        viewModel.fetchSponsorData()

        return binding.root
    }
}