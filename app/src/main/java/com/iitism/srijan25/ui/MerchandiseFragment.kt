package com.iitism.srijan25.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.appbar.MaterialToolbar
import com.iitism.srijan25.R

class MerchandiseFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_merchandise, container, false)

        val toolbar = view.findViewById<MaterialToolbar>(R.id.toolbar)
        val btnPlace = view.findViewById<Button>(R.id.PlaceOrder)

        toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        btnPlace.setOnClickListener {
            openGoogleForm()
        }

        return view
    }

    private fun openGoogleForm() {
        val formUrl = "https://forms.gle/Lh8Divrwx2TbXpbQ6"
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(formUrl)
        startActivity(intent)
    }
}

