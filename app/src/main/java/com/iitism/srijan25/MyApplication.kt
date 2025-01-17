package com.iitism.srijan25

import android.app.Application

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        //FirebaseApp.initializeApp(this)
    }
}