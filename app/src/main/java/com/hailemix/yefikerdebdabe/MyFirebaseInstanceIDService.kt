package com.hailemix.yefikerdebdabe

import android.app.Service
import android.content.Intent
import android.os.IBinder

class MyFirebaseInstanceIDService : Service() {
    override fun onBind(intent: Intent): IBinder? {
        return null
    }
}