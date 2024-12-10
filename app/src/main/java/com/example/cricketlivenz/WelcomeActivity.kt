//package com.example.cricketlivenz
//
//import android.content.Intent
//import android.os.Bundle
//import android.os.Handler
//import android.os.Looper
//import androidx.appcompat.app.AppCompatActivity
//import androidx.databinding.DataBindingUtil
//import com.example.cricketlivenz.databinding.ActivityWelcomeBinding
//
//
//class WelcomeActivity : AppCompatActivity() {
//    private lateinit var binding : ActivityWelcomeBinding
//   // private lateinit var mySharedPreferences: MySharedPreferences
//    private val layouts = IntArray(3)
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//      //  mySharedPreferences = MySharedPreferences.getInstance(this)!!
//        binding = DataBindingUtil.setContentView(this, R.layout.activity_welcome)
//        initUI()
//    }
//
//    private fun initUI(){
//        layouts[0] = R.layout.welcome_slide1
//        layouts[1] = R.layout.welcome_slide2
//        layouts[2] = R.layout.welcome_slide3
//        //val welcomeScreenAdapter = WelcomeScreenAdapter(this, layouts)
//       // binding.viewPagerWelcome.adapter = welcomeScreenAdapter
//      //  binding.dotsIndicator.setViewPager(binding.viewPagerWelcome)
//
//
//        binding.btnNext.setOnClickListener {
//            if (binding.viewPagerWelcome.currentItem == 0) {
//                setCurrentPage(1)
//            }else if (binding.viewPagerWelcome.currentItem == 1) {
//                setCurrentPage(2)
//            }else if (binding.viewPagerWelcome.currentItem == 2) {
//                startActivity(Intent(this@WelcomeActivity, LoginActivity::class.java))
//                finish()
//                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_in_left)
//            }
//        }
//    }
//
//    private fun setCurrentPage(pageNumber: Int){
//        binding.viewPagerWelcome.setPageTransformer(true, CubeOutPageTransformer())
//        binding.viewPagerWelcome.setCurrentItem(pageNumber, true)
//    }
//}