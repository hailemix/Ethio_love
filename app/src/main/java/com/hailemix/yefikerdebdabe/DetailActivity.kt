package com.hailemix.yefikerdebdabe

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.InterstitialAd
import com.google.android.gms.ads.MobileAds
import com.google.android.material.floatingactionbutton.FloatingActionButton


/**
 * Created by user on 1/5/18.
 */

class DetailActivity : AppCompatActivity() {


    private var fontStyle : Typeface ?= null
    private lateinit var mInterstitialAd : InterstitialAd
    private lateinit var mAdView : AdView


    companion object {

        private var detailCounter = 0
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail)

        MobileAds.initialize(this,"ca-app-pub-9156727777369518~6960929170")
        mInterstitialAd = InterstitialAd(this)
        mInterstitialAd.adUnitId = "ca-app-pub-9156727777369518/9473264646"
        mInterstitialAd.loadAd(AdRequest.Builder().build())

        mAdView = findViewById(R.id.myBannerAd)
        mAdView.loadAd(AdRequest.Builder().build())

        val mText = findViewById<TextView>(R.id.myDetail)
        mText.text = MainActivity.titleOfContent

        val interstitialBreak = findViewById<RelativeLayout>(R.id.detailInterstitialBreak)
        val myScrollView : NestedScrollView = findViewById(R.id.scrollController)
        val floatingShareButton = findViewById<FloatingActionButton>(R.id.shareFloatingButton)

        fontStyle =  Typeface.createFromAsset(assets,"fonts/amharic.ttf")
        mText.typeface = fontStyle

        myScrollView.setOnScrollChangeListener { _: NestedScrollView?, _: Int, scrollY: Int, _: Int, oldScrollY: Int ->

            if(scrollY >= oldScrollY) {

                floatingShareButton.postDelayed({

                    floatingShareButton.hide()
                },
                        1000)
            } else {

                floatingShareButton.show()

            }

        }


        val shareButton = findViewById<FloatingActionButton>(R.id.shareFloatingButton)

        shareButton.setOnClickListener {

            detailCounter += 1

            if(detailCounter % 3 == 0){

                interstitialBreak.visibility = View.VISIBLE
                floatingShareButton.hide()


                Handler().postDelayed({

                    if (mInterstitialAd.isLoaded) {

                        mInterstitialAd.show()
                    }

                },3000)

                Handler().postDelayed({


                    interstitialBreak.visibility = View.GONE
                    floatingShareButton.show()

                },4000)


            } else {

                val intent = Intent()
                intent.action = Intent.ACTION_SEND
                intent.type = "text/plain"
                intent.putExtra(Intent.EXTRA_TEXT, mText.text.toString())
                startActivity(Intent.createChooser(intent,"ለ..."))

            }


        }


    }


}
