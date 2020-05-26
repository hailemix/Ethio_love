package com.hailemix.yefikerdebdabe

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.os.Handler
import android.util.DisplayMetrics
import android.view.View
import android.widget.FrameLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import com.google.android.gms.ads.*
import com.google.android.material.floatingactionbutton.FloatingActionButton


/**
 * Created by user on 5/26/20.
 */

class DetailActivity : AppCompatActivity() {


    private var fontStyle : Typeface ?= null
    private lateinit var mInterstitialAd : InterstitialAd
    private lateinit var bannerAdView: AdView
    private lateinit var adContainerView: FrameLayout
    private val adSize: AdSize
        get() {
            val display = windowManager.defaultDisplay
            val outMetrics = DisplayMetrics()
            display.getMetrics(outMetrics)

            val density = outMetrics.density
            var adWidthPixels = bannerAdView.width.toFloat()
            if (adWidthPixels == 0f) {
                adWidthPixels = outMetrics.widthPixels.toFloat()
            }
            val adWidth = (adWidthPixels / density).toInt()
            return AdSize.getPortraitAnchoredAdaptiveBannerAdSize(this, adWidth)
        }

    companion object {

        private var detailCounter = 0
        private const val AD_UNIT_ID = "ca-app-pub-9156727777369518/1351232045"   // Real Banner Ad
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail)
        MobileAds.initialize(this)

        mInterstitialAd = InterstitialAd(this)
        mInterstitialAd.adUnitId = "ca-app-pub-9156727777369518/9473264646"  // Real Interstitial Ad
        mInterstitialAd.loadAd(AdRequest.Builder().build())

        adContainerView = findViewById(R.id.myAdaptiveBanner)
        bannerAdView = AdView(this)
        adContainerView.addView(bannerAdView)
        loadBanner()
        val zContent = intent.getStringExtra("theContent")
        val mText = findViewById<TextView>(R.id.myDetail)
        mText.text = zContent

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

    private fun loadBanner() {
        bannerAdView.adUnitId = AD_UNIT_ID
        bannerAdView.adSize = adSize
        val adRequest = AdRequest.Builder().build()
        bannerAdView.loadAd(adRequest)
    }
}
