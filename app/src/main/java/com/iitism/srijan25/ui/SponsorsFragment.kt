package com.iitism.srijan25.ui

import android.app.Dialog
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.iitism.srijan25.R
import com.iitism.srijan25.viewModel.SponsorViewModel
import com.iitism.srijan25.adapter.SponsorRVAdapter
import com.iitism.srijan25.databinding.FragmentSponsorsBinding


class SponsorsFragment : Fragment() {
    private lateinit var binding: FragmentSponsorsBinding
    private lateinit var viewModel: SponsorViewModel
    private lateinit var adapter: SponsorRVAdapter
    private lateinit var dialog: Dialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSponsorsBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(
            requireActivity(),
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        )[SponsorViewModel::class.java]

        dialog = Dialog(requireActivity())
        dialog.setContentView(R.layout.progress_bar)
        dialog.setCancelable(false)
        val layoutParams = WindowManager.LayoutParams().apply {
            width = WindowManager.LayoutParams.MATCH_PARENT
            height = WindowManager.LayoutParams.MATCH_PARENT
        }
        dialog.window?.attributes = layoutParams
        if (dialog.window != null) {
            dialog.window!!.setBackgroundDrawable(
                ColorDrawable(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.bg
                    )
                )
            )
            dialog.window!!.setBackgroundDrawableResource(R.color.transparent)

        }

        binding.rvSponsors.layoutManager = LinearLayoutManager(context)
        binding.rvSponsors.setHasFixedSize(true)

        adapter = SponsorRVAdapter(emptyList()) { redirectURL ->
            openUrlInBrowser(redirectURL)
        }

        binding.rvSponsors.adapter = adapter

        viewModel.showLoading.observe(viewLifecycleOwner) { showLoading ->
            if (showLoading) {
                dialog.show()
            } else {
                dialog.dismiss()
            }
        }

        viewModel.sponsorData.observe(viewLifecycleOwner) { data ->
            adapter.setData(data)
        }

        viewModel.fetchSponsorData()

        return binding.root
    }

    private fun openUrlInBrowser(url: String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        if (browserIntent.resolveActivity(requireActivity().packageManager) != null) {
            startActivity(browserIntent)
        } else {
            Toast.makeText(context, url, Toast.LENGTH_SHORT).show()
        }
    }
}
