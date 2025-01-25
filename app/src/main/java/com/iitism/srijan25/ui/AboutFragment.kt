package com.iitism.srijan25.ui

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.VideoView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.appbar.MaterialToolbar
import com.iitism.srijan25.R


class AboutFragment : Fragment() {
    private lateinit var videoView: VideoView
    private lateinit var toolbar: MaterialToolbar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_about, container, false)

        videoView = view.findViewById(R.id.video_view)
        toolbar = view.findViewById(R.id.app_toolbar)

        toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        val uri = Uri.parse(("android.resource://" + "com.iitism.srijan25") + "/" + R.raw.iitism_night_video)
        videoView.setVideoURI(uri)
        return view
    }

    override fun onStart() {
        videoView.start()
        super.onStart()
    }
}