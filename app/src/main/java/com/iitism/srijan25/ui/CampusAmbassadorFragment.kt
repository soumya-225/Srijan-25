package com.iitism.srijan25.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.iitism.srijan25.R
import com.iitism.srijan25.databinding.FragmentCampusAmbassadorBinding


class CampusAmbassadorFragment : Fragment() {

    companion object {
        fun newInstance() = CampusAmbassadorFragment()
    }

    private lateinit var binding : FragmentCampusAmbassadorBinding
    private lateinit var registerButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCampusAmbassadorBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.tvPerks.text = "Internship certificates to all \nperforming CAs.\n\n "+
                "Letter of recommondation to best performing CA's.\n\n"+
                "Free accommodation inside IIT ISM Dhanbad for top performing CA's during the fest.\n\n"+
                "Exclusive Goodies for top performing CA's and a chance to win monetary rewards!"

        registerButton = view.findViewById<Button>(R.id.BtnRegister_campusAmb)
        registerButton.setOnClickListener {
            val registerUrl = "https://unstop.com/o/gzxP8ya?utm_medium=Share&utm_source=shortUrl"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(registerUrl)
            startActivity(intent)
        }
    }
}