package com.iitism.srijan25.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.iitism.srijan25.R
import com.iitism.srijan25.R.id.tvReferalCode
import com.iitism.srijan25.utils.SharedPrefsHelper

class ProfileFragment : Fragment() {
    private lateinit var tvName: TextView
    private lateinit var tvEmail: TextView
    private lateinit var tvReferralCode: TextView
    private lateinit var btnLogout: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        tvName = view.findViewById(R.id.tvName)
        tvEmail = view.findViewById(R.id.tvEmail)
        tvReferralCode = view.findViewById(tvReferalCode)
        btnLogout = view.findViewById(R.id.btnLogout)
        loadUserData()
        btnLogout.setOnClickListener {
            logoutUser()
        }
        return view
    }

    private fun loadUserData() {
        val sharedPref = SharedPrefsHelper(requireContext())
        val user = sharedPref.getUser()

        val name = user?.username.toString()
        val email = user?.email.toString()

        tvName.text = name
        tvEmail.text = email
        if (tvReferralCode.text != null) tvReferralCode.text = sharedPref.getReferral().toString()
    }

    private fun logoutUser() {
        SharedPrefsHelper(requireContext()).clear()
        requireActivity().finish()
        startActivity(Intent(requireContext(), MainActivity::class.java))
    }
}