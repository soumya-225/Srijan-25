package com.iitism.srijan25.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.iitism.srijan25.R
import com.iitism.srijan25.databinding.FragmentAboutUsBinding

class AboutUsFragment : Fragment() {
    private lateinit var binding: FragmentAboutUsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAboutUsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvAboutUs.text = requireContext().getString(R.string.about_us)
        binding.tvConvenerName.text = requireContext().getString(R.string.convener_name)
        binding.tvConvenerMessage.text = requireContext().getString(R.string.convener_message)
        binding.tvCoConvener1Name.text = requireContext().getString(R.string.co_convener_name)
        binding.tvCoConvener1Message.text = requireContext().getString(R.string.co_convener_message)

        Glide.with(requireContext())
            .load("https://res.cloudinary.com/dgpgsuay1/image/upload/v1725778370/concetto24_convener.jpg")
            .placeholder(R.drawable.logo)
            .centerCrop()
            .into(binding.ivConvener)

        Glide.with(requireContext())
            .load("https://res.cloudinary.com/dgpgsuay1/image/upload/v1725778499/concetto24_coconvener.jpg")
            .placeholder(R.drawable.logo)
            .centerCrop()
            .into(binding.ivCoConvener1)
    }
}