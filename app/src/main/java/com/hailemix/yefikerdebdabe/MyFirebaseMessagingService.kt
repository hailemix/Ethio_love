package com.hailemix.yefikerdebdabe
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

/**
 * Created by user on 1/8/18.
 */

class MyFirebaseMessagingService : FirebaseMessagingService(){

    override fun onMessageReceived(remoteMessage: RemoteMessage?) {

        Log.d(TAG,"From:" + remoteMessage!!.from)

        if(remoteMessage.data.isNotEmpty()){

            Log.d(TAG,"Notification payload: " + remoteMessage.data)

            if(remoteMessage.notification != null){

                Log.d(TAG, "Message Notification Body: " + remoteMessage.notification!!.body)
            }

        }
    }

    companion object {

        private const val TAG = "FCM SERVICE"
    }


}
