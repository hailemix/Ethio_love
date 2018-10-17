package com.hailemix.yefikerdebdabe
import android.util.Log
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.FirebaseInstanceIdService

/**
 * Created by user on 1/8/18.
 */

class MyFirebaseInstanceIDService : FirebaseInstanceIdService(){


    override fun onTokenRefresh() {

        val refreshToken = FirebaseInstanceId.getInstance().token
        Log.d(TAG,"Refreshed token: " + refreshToken!!)
        sendRegistrationToServer(refreshToken)

    }

    private fun sendRegistrationToServer(refreshToken: String){

        refreshToken + "Hello"
    }

    companion object {

        private val TAG = "FirebaseIDService"
    }



}
