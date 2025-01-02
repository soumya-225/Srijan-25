package com.iitism.srijan25.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavOptions
import com.iitism.srijan25.R
import androidx.navigation.fragment.findNavController
import com.iitism.srijan25.databinding.FragmentOnBoardingBinding

class OnBoardingFragment : Fragment() {

    private var _binding: FragmentOnBoardingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnBoardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnLogin.setOnClickListener {
            val navOptions = NavOptions.Builder()
                .setPopUpTo(R.id.onboardingFragment, false)
                .build()

            findNavController().navigate(R.id.action_onboardingFragment_to_loginFragment, null, navOptions)
        }

        binding.btnRegister.setOnClickListener {
            val navOptions = NavOptions.Builder()
                .setPopUpTo(R.id.onboardingFragment, false)
                .build()

            findNavController().navigate(R.id.action_onboardingFragment_to_registerFragment, null, navOptions)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}