package com.example.cricketlivenz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.TextUtils
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.cricketlivenz.databinding.ActivitySplashBinding


class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
   // private lateinit var mySharedPreferences: MySharedPreferences
    private var isNotificationClicked = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)
        setLoopHandlerSplash()
    }

    private fun setLoopHandlerSplash() {
        //Glide.with(binding.txtNoDataImage.context).asGif().load(R.drawable.logo).fitCenter().into(binding.txtNoDataImage)

        Handler(Looper.myLooper()!!).postDelayed({
            checkStatus()
        }, 1500)
    }

    private fun checkStatus() {
        startActivity(Intent(this@SplashActivity, MainActivity::class.java))
        finish()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_in_left)
//        if (mySharedPreferences == null || TextUtils.isEmpty(mySharedPreferences.userObjectModel?.user_id)) {
//            startActivity(Intent(this@SplashActivity, WelcomeActivity::class.java))
//            finish()
//            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_in_left)
//        } else {
//            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
//            finish()
//            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_in_left)
//        }
    }
}