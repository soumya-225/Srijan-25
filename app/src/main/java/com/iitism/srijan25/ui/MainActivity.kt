package com.iitism.srijan25.ui

import android.app.Dialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.iitism.srijan25.R
import com.iitism.srijan25.auth.LoginSignup
import com.iitism.srijan25.databinding.ActivityMainBinding
import com.iitism.srijan25.utils.SharedPrefsHelper


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var dialog: Dialog
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var sharedPrefsHelper: SharedPrefsHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initializeDialog()
        askNotificationAndSmsPermission()

        sharedPrefsHelper = SharedPrefsHelper(this)
        val user = sharedPrefsHelper.getUser()

        binding.appBar.btnProfilehome.setOnClickListener {
            if (user == null) {
                startActivity(Intent(this, LoginSignup::class.java))
            } else {
                findNavController(R.id.nav_host_fragment_content_main).navigate(R.id.profileFragment)
            }
        }

        dialog.show()

        dismissDialogAfterDelay()

        navController = findNavController(R.id.nav_host_fragment_content_main)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.homeFragment,
                R.id.eventsFragment,
                R.id.singleEventFragment,
                R.id.announcementsFragment,
                R.id.merchandiseFragment,
                R.id.sponsorsFragment,
                R.id.profileFragment,
                R.id.aboutUsFragment,
                R.id.coreTeamFragment,
                R.id.contactFragment,
                R.id.campusAmbassadorFragment
            ), binding.drawerLayout
        )

        binding.appBar.btnMenu.setOnClickListener {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }

        navController.addOnDestinationChangedListener { _, destination, _ ->

            when (destination.id) {
                R.id.homeFragment -> binding.navView.setCheckedItem(R.id.homeFragment)
                R.id.eventsFragment -> binding.navView.setCheckedItem(R.id.eventsFragment)
                R.id.singleEventFragment -> binding.navView.setCheckedItem(R.id.eventsFragment)
                R.id.announcementsFragment -> binding.navView.setCheckedItem(R.id.announcementsFragment)
                R.id.merchandiseFragment -> binding.navView.setCheckedItem(R.id.merchandiseFragment)
                R.id.sponsorsFragment -> binding.navView.setCheckedItem(R.id.sponsorsFragment)
                R.id.profileFragment -> {
                    if (user == null) {
                        finish()
                        startActivity(Intent(this, MainActivity::class.java))
                        Toast.makeText(this, "Login First", Toast.LENGTH_LONG).show()
                        startActivity(Intent(this, LoginSignup::class.java))
                    } else {
                        binding.navView.setCheckedItem(R.id.profileFragment)
                    }
                }

                R.id.aboutUsFragment -> binding.navView.setCheckedItem(R.id.aboutUsFragment)
                R.id.coreTeamFragment -> binding.navView.setCheckedItem(R.id.coreTeamFragment)
                R.id.contactFragment -> binding.navView.setCheckedItem(R.id.contactFragment)
                R.id.campusAmbassadorFragment -> binding.navView.setCheckedItem(R.id.campusAmbassadorFragment)
                else -> binding.navView.setCheckedItem(R.id.homeFragment)
            }
            binding.appBar.tvTitle.text = when (destination.id) {
                R.id.homeFragment -> "Home"
                R.id.eventsFragment -> "Events"
                R.id.announcementsFragment -> "Announcements"
                R.id.merchandiseFragment -> "Merchandise"
                R.id.sponsorsFragment -> "Past Sponsors"
                R.id.profileFragment -> "Profile"
                R.id.aboutUsFragment -> "About Us"
                R.id.coreTeamFragment -> "Core Team"
                R.id.contactFragment -> "Contact Us"
                R.id.singleEventFragment -> "Events"
                R.id.campusAmbassadorFragment -> "Campus Ambassador"
                R.id.guestTalkFragment -> "Guest Talks"
                R.id.GalleryFragment -> "Gallery"
                R.id.mainStageFragment -> "Main Stage"
                R.id.ScheduleFragment -> "Schedule"
                else -> "Concetto 24"
            }

            if (destination.id != R.id.homeFragment) {
                binding.appBar.btnProfilehome.visibility = View.GONE
                binding.appBar.btnLogOut.visibility = View.GONE
            } else {
                if (user == null) {
                    binding.appBar.btnLogOut.visibility = View.GONE
                    binding.appBar.btnProfilehome.visibility = View.VISIBLE
                } else {
                    binding.appBar.btnLogOut.visibility = View.GONE
                    binding.appBar.btnProfilehome.visibility = View.VISIBLE
                }
            }
        }

        binding.navView.setupWithNavController(navController)
        binding.navView.setCheckedItem(R.id.homeFragment)
    }

    private fun dismissDialogAfterDelay() {
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            dialog.dismiss()
        }, 2000)
    }

    private fun initializeDialog() {
        dialog = Dialog(this)
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
                        this,
                        R.color.progress_bar
                    )
                )
            )
        }
    }

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission(),
    ) { isGranted: Boolean ->
        if (isGranted) {
            Log.d("T", "granted")
        }
    }

    private fun askNotificationAndSmsPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val notificationPermission = android.Manifest.permission.POST_NOTIFICATIONS

            if (ContextCompat.checkSelfPermission(
                    this,
                    notificationPermission
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                requestPermissionLauncher.launch(notificationPermission)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}

