//package com.example.cricketlivenz.Fragments
//
//import android.os.Bundle
//import android.os.Handler
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.fragment.app.Fragment
//import androidx.fragment.app.FragmentActivity
//import androidx.viewpager2.widget.ViewPager2
//import com.example.cricketlivenz.databinding.FragmentAboutTradrBinding
////
//import java.util.Timer
//import java.util.TimerTask
//
//class AboutTradrFragment : Fragment() {
//
//    private lateinit var binding: FragmentAboutTradrBinding
//    private lateinit var fragmentActivity: FragmentActivity
//    private var bannerList = ArrayList<Banner>()
//    private lateinit var timer2Middle: Timer
//    private val DELAY_MS1: Long = 3000
//    private val PERIOD_MS1: Long = 7000
//    var currentPageMiddle = 0
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        binding = FragmentAboutTradrBinding.inflate(inflater, container, false)
//        fragmentActivity = context as FragmentActivity
////        getAboutUsData()
//        initData()
//        return binding.root
//    }
//
//    private fun initData() {
//        val retrofit: Retrofit? = RetrofitClient.getInstance(false)
//        val request: WebServices? = retrofit?.create(WebServices::class.java)
//        val call: Call<SuccessModel>? = request?.getBanners("bottom")
//        call?.enqueue(object : Callback<SuccessModel?> {
//            override fun onResponse(call: Call<SuccessModel?>, response: Response<SuccessModel?>) {
//                if (response.isSuccessful && response.body() != null
//                    && response.body()?.success.equals("success") &&
//                    response.body()?.message.equals("Banners")
//                ) {
//                    response.body()?.let {
//                        bannerList.addAll(it.Banners)
//                    }
//
//                    val imageViewerAdapter = BannerSliderAdapter(bannerList)
//                    binding.imageViewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
//                    binding.imageViewPager.adapter = imageViewerAdapter
//
//
////        val imageViewerAdapter = ImageSliderAdapter(imagesList)
////        val homeViewPager = binding.imageViewPager
////        homeViewPager.adapter = imageViewerAdapter
//                    val indicator2: CircleIndicator3 = binding.dotsIndicator
//                    indicator2.setViewPager(binding.imageViewPager)
//                    val handler = Handler()
//                    val Update = Runnable {
//                        if (currentPageMiddle === (bannerList.size + 1) - 1) {
//                            currentPageMiddle = 0
//                        }
//                        binding.imageViewPager.setCurrentItem(currentPageMiddle++, true)
//                    }
//                    timer2Middle = Timer()
//
//                    timer2Middle.schedule(object : TimerTask() {
//                        override fun run() {
//                            handler.post(Update)
//                        }
//                    }, DELAY_MS1, PERIOD_MS1)
//                }
//            }
//
//            override fun onFailure(call: Call<SuccessModel?>, t: Throwable) {
//                t.printStackTrace()
//            }
//
//        })
//
//
//    }
//
////    private fun getAboutUsData(){
////        val retrofit: Retrofit? = RetrofitClient.getInstance(false)
////        val request: WebServices? = retrofit?.create(WebServices::class.java)
////        val call: Call<SuccessModel>? = request?.getAboutUsData()
////        call?.enqueue(object : Callback<SuccessModel?> {
////            override fun onResponse(call: Call<SuccessModel?>, response: Response<SuccessModel?>) {
////                if (response.isSuccessful && response.body() != null
////                    && response.body()?.success.equals("success") &&
////                    response.body()?.message.equals("About")
////                ) {
////                    var aboutList = ArrayList<About>()
////                    response.body()?.let {
////                        aboutList.addAll(it.About)
////                    }
////
////                    if (aboutList == null || aboutList.isEmpty()){}else{
////                        val aboutObj = aboutList.get(0)
////                        println("aboutObj==== $aboutObj")
////                        Glide.with(binding.imgPhoto.context)
////                            .load(aboutObj.about_image).apply(
////                                RequestOptions().diskCacheStrategy(
////                                    DiskCacheStrategy.ALL))
////                            .into(binding.imgPhoto)
////
////                        binding.about.setText(aboutObj.about)
////                    }
////                }
////            }
////
////            override fun onFailure(call: Call<SuccessModel?>, t: Throwable) {t.printStackTrace()}
////
////        })
////    }
//}