package com.iitism.srijan25.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.iitism.srijan25.R
import com.iitism.srijan25.databinding.FragmentAboutUsBinding

class AboutUsFragment : Fragment() {

    companion object {
        fun newInstance() = AboutUsFragment()
    }

    private lateinit var binding : FragmentAboutUsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAboutUsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvAboutUs.text="CONCETTO is the premier annual Techno-Management Fest hosted by the " +
                "prestigious Indian Institute of Technology (Indian School of Mines), " +
                "Dhanbad. It stands as a celebration of innovation and talent, merging " +
                "technology and management prowess. With the largest participation in " +
                "Eastern India, CONCETTO attracts over 20,000 enthusiasts each year." +
                " More than just an event, CONCETTO is a dynamic platform where " +
                "participants challenge their technical acumen, broaden their " +
                "horizons through expert workshops, and enjoy electrifying " +
                "performances as the sun sets." +
                " This year, join us for CONCETTO '24, where we invite you to explore new " +
                "horizons from 4th to 6th October. Stay tuned as we unveil an exciting " +
                "new theme: ‘VINTAGE VISIONS IN A MODERN MATRIX’ . The fest promises a " +
                "diverse array of technical and managerial events that will leave you " +
                "enthralled." +
                " CONCETTO fuels the dreams of engineering enthusiasts, providing a " +
                "launchpad for innovation and excellence. Participants stand to win " +
                "certificates, exciting goodies, and prizes worth lakhs. Step into a " +
                "world where technology meets creativity, where learning is a " +
                "celebration, and every moment weaves unforgettable memories.\n"

        binding.tvConvenerName.text="Prof. Ajit Kumar"

        binding.tvConvenerMessage.text="As Edward Teller once said, \"The science of today is the technology of " +
                "tomorrow.\" In an era where the future is constantly being reimagined, " +
                "today's youth yearn for a stage to showcase their unique talents. " +
                "CONCETTO'24: Weaving Timeless Innovation offers such a stage, where " +
                "tradition meets modernity. This year's theme, \"Vintage Vision in " +
                "Modern Matrix,\" celebrates the harmony between classic ingenuity and " +
                "contemporary technology. The fest pays tribute to the enduring " +
                "legacy of vintage innovations while providing a platform for " +
                "students to merge time-honored principles with cutting-edge ideas. " +
                "This extraordinary event is a testament to the tireless efforts of " +
                "our dedicated team and the generous support of our sponsors, who " +
                "have made it all possible.\n"

        binding.tvCoConvener1Name.text="Prof. Himanshu Bhusan Mishra"

        binding.tvCoConvener1Message.text = "Building tomorrow on the foundations of yesterday, CONCETTO'24: " +
                "Weaving Timeless Innovation provides a platform where the " +
                "brilliance of young minds meets the wisdom of the past. Under this " +
                "year's theme,\n 'Vintage Vision in Modern Matrix,'\n we explore how the " +
                "echoes of classic innovations can inspire and shape the cutting-edge " +
                "technologies of today. This event is not just a celebration of " +
                "creativity and ingenuity but also a convergence of tradition and " +
                "modernity. As Co-Conveyor, it is a privilege to be part of this journey, " +
                "ensuring that the fest remains a beacon of inspiration, where the " +
                "legacies of yesterday fuel the breakthroughs of tomorrow. We are " +
                "deeply grateful to our dedicated team and sponsors, whose efforts " +
                "have made this vision a reality.\n"

       // binding.tvCoConvener2Name.text="Prof. Suresh K Yatiraju"

       // binding.tvTreasurerName.text="Prof. Madhulika Gupta"

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

//        Glide.with(requireContext())
//            .load("https://res.cloudinary.com/dkdratnao/image/upload/v1704403824/Co-convenor2_qp0smn.png")
//            .placeholder(R.drawable.logo)
//            .centerCrop()
//            .into(binding.ivCoConvener2)
//
//        Glide.with(requireContext())
//            .load("https://res.cloudinary.com/dkdratnao/image/upload/v1704473056/madhulika_gupta_ye3xav.jpg")
//            .placeholder(R.drawable.logo)
//            .centerCrop()
//            .into(binding.ivTreasurer)
    }
}