package com.iitism.srijan25.auth

import android.app.Dialog
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.iitism.srijan25.R
import com.iitism.srijan25.databinding.FragmentLoginBinding
import com.iitism.srijan25.model.LoginRequest
import com.iitism.srijan25.model.LoginResponse
import com.iitism.srijan25.api.AuthClient
import com.iitism.srijan25.ui.MainActivity
import com.iitism.srijan25.utils.SharedPrefsHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.regex.Pattern


class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var dialog: Dialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        initializeDialog()
        binding = FragmentLoginBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeDialog()

        binding.registerTextView.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)

        }

        binding.btnLogin.setOnClickListener {
            if (checkForm())
                login()
        }
    }

    private fun login() {
        dialog.show()

        val email = binding.etEmail.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()

        val loginRequest = LoginRequest(email, password)
        val call = AuthClient.authService.login(loginRequest)

        call.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                dialog.dismiss()
                Log.d("Register", response.code().toString())
                when(response.code()) {
                    200 -> {
                        dialog.dismiss()
                        Toast.makeText(context, "Logged In Successfully!", Toast.LENGTH_SHORT).show()

                        val responseBody = response.body()
                            val user = responseBody?.user
                            val accessToken = responseBody?.accessToken
                            val refreshToken = responseBody?.refreshToken

                            Log.d("Login AT", accessToken.toString())
                            Log.d("Login RT", refreshToken.toString())

                            val prefsHelper = SharedPrefsHelper(requireContext())
                            prefsHelper.saveUser(user)
                            prefsHelper.saveAccessToken(accessToken)
                            prefsHelper.saveRefreshToken(refreshToken)

                            val intent = Intent(requireContext(), MainActivity::class.java)
                            startActivity(intent)
                            requireActivity().finish()
                    }

                    401 -> {
                        dialog.dismiss()
                        Toast.makeText(context, "Incorrect Email or Password.", Toast.LENGTH_SHORT).show()
                    }

                    403 -> {
                        dialog.dismiss()
                        Toast.makeText(context, "Please verify your account first", Toast.LENGTH_SHORT).show()

                        val responseData = response.body()

                        if (responseData == null) {
                            dialog.dismiss()
                            response.errorBody()?.let { errorBody ->
                                val errorBodyString = errorBody.string()
                                Log.d("Login", "Error Body: $errorBodyString")

                                try {
                                    dialog.dismiss()
                                    val parsedErrorResponse = Gson().fromJson(errorBodyString, LoginResponse::class.java)

                                    val user = parsedErrorResponse.user
                                    val accessToken = parsedErrorResponse.accessToken
                                    val refreshToken = parsedErrorResponse.refreshToken

                                    if (user != null && accessToken != null && refreshToken != null) {
                                        Log.d("Login AT", accessToken)
                                        Log.d("Login RT", refreshToken)

                                        val prefsHelper = SharedPrefsHelper(requireContext())
                                        prefsHelper.saveUser(user)
                                        prefsHelper.saveAccessToken(accessToken)
                                        prefsHelper.saveRefreshToken(refreshToken)

                                        val action = LoginFragmentDirections.actionLoginFragmentToOtpFragment(user.email)
                                        findNavController().navigate(action)
                                    } else {
                                        dialog.dismiss()
                                        Toast.makeText(context, "Something went wrong!", Toast.LENGTH_SHORT).show()
                                    }
                                } catch (e: JsonSyntaxException) {
                                    dialog.dismiss()
                                    Toast.makeText(context, "Error parsing server response", Toast.LENGTH_SHORT).show()
                                }
                            } ?: run {
                                dialog.dismiss()
                                Toast.makeText(context, "Something went wrong...", Toast.LENGTH_SHORT).show()
                            }
                        } else {
                            dialog.dismiss()
                            val user = responseData.user
                            val accessToken = responseData.accessToken
                            val refreshToken = responseData.refreshToken

                            if (user != null && accessToken != null && refreshToken != null) {
                                val prefsHelper = SharedPrefsHelper(requireContext())
                                prefsHelper.saveUser(user)
                                prefsHelper.saveAccessToken(accessToken)
                                prefsHelper.saveRefreshToken(refreshToken)

                                val action = LoginFragmentDirections.actionLoginFragmentToOtpFragment(user.email)
                                findNavController().navigate(action)
                            } else {
                                dialog.dismiss()
                                Toast.makeText(context, "Something went wrong!", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }

                    else -> {
                        dialog.dismiss()
                        Toast.makeText(context, "Unexpected Error Occurred", Toast.LENGTH_SHORT).show()
                        Log.d("Register", "Unexpected Error Occurred ${response.code()}")
                    }
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                // Handle failure
            }
        })
    }

    private fun showToastAndReturnFalse(message: String): Boolean {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        return false
    }

    private fun checkForm(): Boolean {
        val email = binding.etEmail.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()

        return when {
            email.isEmpty() -> showToastAndReturnFalse("Please enter your email!")
            password.isEmpty() -> showToastAndReturnFalse("Please enter your password!")
            !isValidEmail(email) -> showToastAndReturnFalse("Entered email is invalid!")
            else -> true
        }
    }

    private fun isValidEmail(email: String): Boolean {
        val baseRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"
        return Pattern.matches(baseRegex, email)
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
            dialog.window!!.setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(requireContext(), R.color.progress_bar)))
        }
    }
}