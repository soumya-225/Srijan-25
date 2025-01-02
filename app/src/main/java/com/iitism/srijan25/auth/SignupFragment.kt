package com.iitism.srijan25.auth

import android.app.Dialog
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson
import com.iitism.srijan25.R
import com.iitism.srijan25.databinding.FragmentSignupBinding
import com.iitism.srijan25.models.RegisterRequest
import com.iitism.srijan25.models.RegisterResponse
import com.iitism.srijan25.services.AuthClient
import com.iitism.srijan25.utils.SharedPrefsHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SignupFragment : Fragment() {

    private var _binding:FragmentSignupBinding? = null
    private val binding get() = _binding!!
    private lateinit var prefsHelper: SharedPrefsHelper
    private lateinit var dialog: Dialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        initializeDialog()
        _binding = FragmentSignupBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeDialog()
        binding.tvLogin.setOnClickListener {
//            val navOptions = NavOptions.Builder()
//                .setPopUpTo(R.id.onboardingFragment, false)
//                .build()
//
//            findNavController().navigate(
//                R.id.action_registerFragment_to_loginFragment,
//                null,
//                navOptions
//            )
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }

        binding.btnSignUp.setOnClickListener {
            if (checkForm()) {
                register()
            }
        }

    }

    private fun register() {
        dialog.show()
        val fullName = binding.etName.text.toString().trim()
        val email = binding.etEmail.text.toString().trim()
        val college = binding.etCollege.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()
        val isISM = binding.rbYes.isChecked
        val refCode = if (binding.etReferralCode.text.toString().isNotEmpty())
            binding.etReferralCode.text.toString().trim()
        else null

        val registerRequest = RegisterRequest(fullName, password, email, isISM, college, refCode)
        val call = AuthClient.authService.register(registerRequest)

        call.enqueue(object : Callback<RegisterResponse> {
            override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {
                dialog.dismiss()
                when (response.code()) {
                    200 -> {
                        dialog.dismiss()
                        Toast.makeText(context, "Account Created Successfully!", Toast.LENGTH_SHORT).show()

                        val registerResponse = response.body()
                        if (registerResponse?.user != null && registerResponse.accessToken != null && registerResponse.refreshToken != null) {
                            val user = registerResponse.user
                            val accessToken = registerResponse.accessToken
                            val refreshToken = registerResponse.refreshToken

                            Log.d("Register AT", accessToken)
                            Log.d("Register RT", refreshToken)

                            prefsHelper = SharedPrefsHelper(requireContext())
                            prefsHelper.saveUser(user)
                            prefsHelper.saveAccessToken(accessToken)
                            prefsHelper.saveRefreshToken(refreshToken)

                            val action = SignupFragmentDirections.actionRegisterFragmentToOtpFragment(user.email)
                            findNavController().navigate(action)
                        } else {
                            dialog.dismiss()
                            Toast.makeText(context, "Something went wrong!", Toast.LENGTH_SHORT).show()
                        }
                    }

                    409 -> {
                        dialog.dismiss()
                        response.errorBody()?.let { errorBody ->
                            val errorBodyString = errorBody.string()
                            Log.d("Register", "Error Body: $errorBodyString")

                            val registerResponse = Gson().fromJson(errorBodyString, RegisterResponse::class.java)
                            val errorMessage = registerResponse.message ?: registerResponse.error

                            errorMessage?.let { message ->
                                Log.d("Register", message)
                                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                            } ?: run {
                                Toast.makeText(context, "Conflict Error Occurred...", Toast.LENGTH_SHORT).show()
                            }
                        } ?: run {
                            Toast.makeText(context, "Conflict Error Occurred..", Toast.LENGTH_SHORT).show()
                        }
                    }

                    else -> {
                        dialog.dismiss()
                        Toast.makeText(context, "Unexpected Error Occurred", Toast.LENGTH_SHORT)
                            .show()
                        Log.d("Register", "Unexpected Error Occurred ${response.code()}")
                    }
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                dialog.dismiss()
                Toast.makeText(context, "Network Error Occurred", Toast.LENGTH_SHORT).show()
                Log.d("Register", "Network Error: ${t.message}")
            }
        })
    }

    private fun checkForm(): Boolean {
        return validateName() && validateEmail() && validateCollege() && validatePassword() && validateConfirmPassword() && validatePasswordAndConfirmPassword()
    }

    private fun validateName(): Boolean {
        val name = binding.etName.text.toString().trim()
        var error: String? = null

        if (name.isEmpty()) {
            error = "Please enter your name."
        }

        if (error != null) {
            binding.etName.error = error
            return false
        } else {
            binding.etName.error = null
        }

        return true
    }

    private fun validateEmail(): Boolean {
        var error: String? = null
        val email = binding.etEmail.text.toString().trim()
        if (email.isEmpty()) {
            error = "Please enter your email."
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            error = "Invalid email address."
        } else if (binding.rbYes.isChecked) {
            if (!email.endsWith("@iitism.ac.in")) {
                error = "Invalid IIT ISM id."
            }
        } else if (binding.rbNo.isChecked) {
            if (email.endsWith("@iitism.ac.in")) {
                error = "Invalid email."
            }
        }

        if (error != null) {
            binding.etEmail.error = error
        } else {
            binding.etEmail.error = null
        }

        return error == null
    }

    private fun validateCollege(): Boolean {
        val name = binding.etCollege.text.toString().trim()
        var error: String? = null

        if (name.isEmpty()) {
            error = "Please enter your college."
        }

        if (error != null) {
            binding.etCollege.error = error
            return false
        } else {
            binding.etCollege.error = null
        }

        return true
    }

    private fun validatePassword(): Boolean {
        var error: String? = null
        val password = binding.etPassword.text.toString().trim()
        if (password.isEmpty()) {
            error = "Please enter your password."
        } else if (password.length < 6) {
            error = "Password is too short."
        }

        if (error != null) {
            binding.etPassword.error = error
        } else {
            binding.etPassword.error = null
        }

        return error == null
    }

    private fun validateConfirmPassword(): Boolean {
        var error: String? = null
        val password = binding.etConfirmPassword.text.toString().trim()
        if (password.isEmpty()) {
            error = "Please enter your password."
        } else if (password.length < 6) {
            error = "Password is too short."
        }

        if (error != null) {
            binding.etConfirmPassword.error = error
        } else {
            binding.etConfirmPassword.error = null
        }

        return error == null
    }

    private fun validatePasswordAndConfirmPassword(): Boolean {
        var error: String? = null
        val password = binding.etPassword.text.toString().trim()
        val confirmPassword = binding.etConfirmPassword.text.toString().trim()
        if (password != confirmPassword) {
            error = "Passwords do not match."
        }

        if (error != null) {
            binding.etConfirmPassword.error = error
        } else {
            binding.etConfirmPassword.error = null
        }

        return error == null
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun initializeDialog() {
        dialog = Dialog(requireContext())
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
                        R.color.progress_bar
                    )
                )
            )
        }
    }

}