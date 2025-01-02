package com.iitism.srijan25.auth

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.iitism.srijan25.R
import com.iitism.srijan25.databinding.FragmentOtpBinding
import com.iitism.srijan25.models.OtpVerificationRequest
import com.iitism.srijan25.models.OtpVerificationResponse
import com.iitism.srijan25.models.ResendOtpRequest
import com.iitism.srijan25.services.AuthClient
import com.iitism.srijan25.ui.MainActivity
import com.iitism.srijan25.utils.SharedPrefsHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OtpFragment : Fragment() {

    private var _binding: FragmentOtpBinding? = null
    private val binding get() = _binding!!

    private lateinit var email: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOtpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setEditTextListeners(
            binding.editText1,
            binding.editText2,
            binding.editText3,
            binding.editText4,
            binding.editText5,
            binding.editText6
        )

        email = arguments?.getString("email") ?: ""
        binding.tvCollegeEmail.text = "Please enter the OTP sent to\n $email "

        binding.btnVerifyOtp.setOnClickListener {
            val otp = getOtpFromEditTexts(
                binding.editText1,
                binding.editText2,
                binding.editText3,
                binding.editText4,
                binding.editText5,
                binding.editText6
            )

            if (otp.length != 6) {
                Toast.makeText(context, "Invalid OTP", Toast.LENGTH_SHORT).show()
            } else {

                verifyOtp(otp, email)
            }
        }

        binding.tvResendOtp.setOnClickListener {
            resendOtp()
        }
    }

//    @SuppressLint("SetTextI18n")
    private fun resendOtp() {
        val request = ResendOtpRequest(email)
        val call = AuthClient.authService.resendOtp(request)

        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                when (response.code()) {
                    200 -> {
                        Toast.makeText(context, "OTP Sent Successfully", Toast.LENGTH_SHORT).show()

                        binding.tvResendOtp.isEnabled = false
                        binding.tvResendOtp.setTextColor(
                            ContextCompat.getColor(
                                requireContext(),
                                R.color.primary2
                            )
                        )

                        object : CountDownTimer(30000, 1000) {
                            override fun onTick(millisUntilFinished: Long) {
                                binding.tvResendOtp.text =
                                    "Resend OTP in ${millisUntilFinished / 1000} sec"
                            }

                            override fun onFinish() {
                                binding.tvResendOtp.isEnabled = true
                                binding.tvResendOtp.text = "Resend OTP"
                                binding.tvResendOtp.setTextColor(
                                    ContextCompat.getColor(
                                        requireContext(),
                                        R.color.primaryDark
                                    )
                                )
                            }
                        }.start()
                    }

                    else -> {
                        Toast.makeText(context, "Something Went Wrong...", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(context, "Network Error Occurred", Toast.LENGTH_SHORT).show()
                Log.d("Register", "Network Error: ${t.message}")
            }

        })
    }

    private fun verifyOtp(otp: String, email: String) {
        val request = OtpVerificationRequest(otp, email)
        val call = AuthClient.authService.verifyOtp(request)

        call.enqueue(object : Callback<OtpVerificationResponse> {
            override fun onResponse(
                call: Call<OtpVerificationResponse>,
                response: Response<OtpVerificationResponse>
            ) {
                when (response.code()) {
                    200 -> {
                        val body = response.body()
                        if (body != null && body.success == true && body.userData != null) {
                            Toast.makeText(
                                context,
                                "OTP Verified Successfully!",
                                Toast.LENGTH_SHORT
                            ).show()

                            val sharedPrefsHelper = SharedPrefsHelper(requireContext())
                            sharedPrefsHelper.saveUser(body.userData)

                            val intent = Intent(requireContext(), MainActivity::class.java)
                            startActivity(intent)
                            requireActivity().finish()
                        } else {
                            Toast.makeText(
                                context,
                                body?.message ?: "OTP does not match!",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                    401 -> {
                        Toast.makeText(context, "Please Register First!", Toast.LENGTH_SHORT).show()
                    }

                    410 -> {
                        Toast.makeText(
                            context,
                            "Otp has Expired, Resend Otp Please!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    else -> {
                        Toast.makeText(context, "Unexpected Error Occurred!", Toast.LENGTH_SHORT)
                            .show()
                        Log.d("OTP", response.code().toString())
                    }
                }
            }

            override fun onFailure(call: Call<OtpVerificationResponse>, t: Throwable) {
                Toast.makeText(context, "Network Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun setEditTextListeners(vararg editTexts: EditText) {
        for ((index, editText) in editTexts.withIndex()) {
            editText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    charSequence: CharSequence,
                    start: Int,
                    before: Int,
                    count: Int
                ) {
                    // Do nothing
                }

                override fun onTextChanged(
                    charSequence: CharSequence,
                    start: Int,
                    before: Int,
                    count: Int
                ) {
                    if (charSequence.length == 1 && index < editTexts.size - 1) {
                        editTexts[index + 1].requestFocus()
                    } else if (charSequence.isEmpty() && index > 0) {
                        editTexts[index - 1].requestFocus()
                    } else if (charSequence.length == 1 && index == editTexts.size - 1) {
                        hideKeyboard()
                    }
                }

                override fun afterTextChanged(editable: Editable) {
                    // Do nothing
                }
            })
        }
    }

    private fun hideKeyboard() {
        val inputMethodManager =
            requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(requireActivity().currentFocus?.windowToken, 0)
    }

    private fun getOtpFromEditTexts(vararg editTexts: EditText): String {
        val otpStringBuilder = StringBuilder()
        for (editText in editTexts) {
            otpStringBuilder.append(editText.text)
        }
        return otpStringBuilder.toString()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}