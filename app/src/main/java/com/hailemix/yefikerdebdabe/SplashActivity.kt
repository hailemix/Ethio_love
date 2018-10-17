package com.hailemix.yefikerdebdabe

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView

/**
 * Created by user on 1/8/18.
 */

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash)

        val splashImage = findViewById<ImageView>(R.id.splashPicture)
        val firstAnimation = AnimationUtils.loadAnimation(baseContext, R.anim.abc_fade_in)
        val lastAnimation = AnimationUtils.loadAnimation(baseContext,R.anim.abc_fade_out)

        splashImage.startAnimation(firstAnimation)
        firstAnimation.duration = 2000
        firstAnimation.setAnimationListener(object : Animation.AnimationListener{

            override fun onAnimationRepeat(p0: Animation?) = Unit
            override fun onAnimationStart(p0: Animation?) = Unit
            override fun onAnimationEnd(animation: Animation?) {

                splashImage.startAnimation(lastAnimation)
                lastAnimation.duration = 2800
                finish()

                val intent = Intent(this@SplashActivity, MainActivity::class.java)
                startActivity(intent)

            }


        })
    }


}
