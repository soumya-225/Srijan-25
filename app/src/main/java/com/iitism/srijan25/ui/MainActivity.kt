package com.iitism.srijan25.ui

import android.app.Dialog
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.drawable.ColorDrawable
import android.net.Uri
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
    private val topic = "/topics/announcement"
    private lateinit var binding: ActivityMainBinding
    private lateinit var dialog: Dialog

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var sharedPrefsHelper: SharedPrefsHelper

    private lateinit var tShirtSize: String
    private lateinit var address: String
    private lateinit var quantity: String
    private lateinit var orderId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initializeDialog()
        askNotificationAndSmsPermission()

        val preferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
//        var token = preferences.getString("token", "") ?: ""
//        if (token.isEmpty()) {
//            binding.appBar.btnLogOut.visibility = View.GONE
//            binding.appBar.btnProfilehome.visibility = View.VISIBLE
//        } else {
//            binding.appBar.btnLogOut.visibility = View.VISIBLE
//            binding.appBar.btnProfilehome.visibility = View.GONE
//        }
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

        val isISMite = preferences.getString("isISMite", "") ?: ""



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


//        setupActionBarWithNavController(navController, binding.drawerLayout)
//        binding.navView.setupWithNavController(navController)


//        if (navController.currentDestination?.id == R.id.homeFragment) {
//            binding.appBar.btnProfile.visibility = View.VISIBLE
//            binding.appBar.btnProfile.setOnClickListener {
//                val preferences =
//                    getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
////                val isISMite = preferences.getString("isISMite", "") ?: ""
//                val userId = preferences.getString("userId", "") ?: ""
//
//                if (userId.isEmpty()) {
//                    startActivity(Intent(this, LoginSignupActivity::class.java))
//                } else{
//                navController.navigate(R.id.action_homeFragment_to_profileFragment)
//            }}
//        }


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
                            if(user==null) {
                                finish()
                                startActivity(Intent(this,MainActivity::class.java))
                                Toast.makeText(this,"Login First",Toast.LENGTH_LONG).show()
                                startActivity(Intent(this,LoginSignup::class.java))
                            }else{
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
                R.id.mainStageFragment-> "Main Stage"
                R.id.ScheduleFragment-> "Schedule"
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

//        binding.navView.setNavigationItemSelectedListener { menuItem ->
//            when (menuItem.itemId) {
//                R.id.profileFragment -> navController.navigate(R.id.homeFragment)
//            }
//
//            binding.drawerLayout.closeDrawer(GravityCompat.START)
//            true
//        }

        binding.navView.setupWithNavController(navController)
        binding.navView.setCheckedItem(R.id.homeFragment)

//        Checkout.preload(this)
    }

    override fun onResume() {
        super.onResume()

        val preferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
        val isISMite = preferences.getString("isISMite", "") ?: ""
        val token = preferences.getString("token", "") ?: ""


//        if(token.isNotEmpty()) {
//
//        }
    }

    private fun dismissDialogAfterDelay() {
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            dialog.dismiss()
        }, 2000)
    }
    private fun openPlayStoreForRating() {
        val appPackageName = packageName
        try {
            val uri = "market://details?id=$appPackageName"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            val uri = "https://play.google.com/store/apps/details?id=$appPackageName"
             val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
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
            // FCM SDK (and your app) can post notifications.
            //extract token
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

    private fun getFCMTokenFromSharedPreferences(): String {
        val preferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
        return preferences.getString("fcmToken", "") ?: ""
    }

    private fun saveFCMTokenToSharedPreferences(token: String) {
        val preferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
        preferences.edit().putString("fcmToken", token).apply()
    }

//    private fun obtainAndSendFCMTokenToApi() {
//        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
//            if (task.isSuccessful) {
//                val token = task.result
////                sendToken(token)
//            } else {
//                Log.e("FCM Token", "Failed to get token", task.exception)
//                dialog.dismiss()
//            }
//        }
//    }
//
//    private fun sendToken(token: String) {
//        val tokenApi: TokenApi = TokenRetrofitInstance.tokenApi
//        val addTokenCall: Call<Void> = tokenApi.addToken(AddTokenModel(token))
//
//        addTokenCall.enqueue(object : Callback<Void> {
//            override fun onResponse(call: Call<Void>, response: Response<Void>) {
//                if (response.isSuccessful) {
//                    saveFCMTokenToSharedPreferences(token)
//                    Log.d("Token sent successfully", token)
//                    dialog.dismiss()
//                } else {
//                    dialog.dismiss()
//                }
//            }
//
//            override fun onFailure(call: Call<Void>, t: Throwable) {
//                Log.e("AddToken", "Network request failed", t)
//                dialog.dismiss()
//            }
//
//        })
//    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

//        if (paymentId != null) {
//            Log.d("dataMerch", paymentId)
//        }
}

//    override fun sendOrder(tShirtSize: String, address: String, quantity: String, orderId: String) {
//        Log.d("OKKKKKKKKKKK", "OKKKKKKKKKKKKKKKK")
//        this.tShirtSize = tShirtSize
//        this.address = address
//        this.orderId = orderId
//        this.quantity = quantity
//    }


