package com.hailemix.yefikerdebdabe

import android.content.Intent
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

/**
 * Created by user on 1/8/18.
 */

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash)

        val splashImage = findViewById<ImageView>(R.id.splashPicture)
        val startingAnimation = AnimationUtils.loadAnimation(baseContext, R.anim.splash_animation)


        splashImage.startAnimation(startingAnimation)
        startingAnimation.duration = 2200
        startingAnimation.setAnimationListener(object : Animation.AnimationListener {

            override fun onAnimationRepeat(p0: Animation?) = Unit
            override fun onAnimationStart(p0: Animation?) = Unit
            override fun onAnimationEnd(animation: Animation?) {

                finish()

                val intent = Intent(this@SplashActivity, MainActivity::class.java)
                startActivity(intent)

            }
        })
    }


}
