package com.example.cricketlivenz.Fragments

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide

import com.example.cricketlivenz.R
import com.example.cricketlivenz.databinding.FragmentTradrBinding
class TradrFragment : Fragment() {
    private lateinit var binding: FragmentTradrBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tradr, container, false)
        binding.appBar.logo.visibility = View.VISIBLE
//        Glide.with(binding.appBar.logo.context).asGif().load(R.drawable.tradrhomegif_1).fitCenter().into(binding.appBar.logo)

//        binding.viewPager.isUserInputEnabled = false
//        binding.lockedViewTradrAbout.setOnClickListener(View.OnClickListener {
//            Toast.makeText(binding.lockedViewTradrAbout.context, "Coming Soon", Toast.LENGTH_SHORT).show()
//        })
        //setTablayoutItems()
//        getAboutUsData()
//        setVideo()
        return binding.root
    }

//    private fun getAboutUsData() {
//        val retrofit: Retrofit? = RetrofitClient.getInstance(false)
//        val request: WebServices? = retrofit?.create(WebServices::class.java)
//        val call: Call<SuccessModel>? = request?.getBanners("top")
//
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
//                    val imageViewerAdapter = TradrBannerSliderAdapter(bannerList)
//                    binding.imageViewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
//                    binding.imageViewPager.adapter = imageViewerAdapter
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
//    }
//
//    private fun setVideo() {
//        playerView = binding.tradrVideo
//        val videoPath = "https://tradr.co.in/wp-content/uploads/2022/12/Strategies-Video.mp4"
//
//        val mediaItem = videoPath.let { MediaItem.fromUri(it) }
//        player = SimpleExoPlayer.Builder(binding.tradrVideo.context).build()
//        playerView.player = player
//        if (mediaItem != null) {
//            player?.setMediaItem(mediaItem)
//        }
//        player?.prepare()
//        player?.pause()
//
////        player?.play()
////        binding.tradrVideo.setVideoPath("https://tradr.co.in/wp-content/uploads/2022/12/Strategies-Video.mp4")
////        binding.tradrVideo.start()
////        binding.tradrVideo.setOnPreparedListener{
////            mediaPlayer -> this.mediaPlayer =mediaPlayer
////            mediaPlayer.seekTo(2000)
////            binding.tradrVideo.start()
////        }
////        binding.tradrVideo.setOnCompletionListener { mediaPlayer?.release() }
////        binding.tradrVideo.setOnClickListener(View.OnClickListener {
////            clickCount++
////            when (clickCount) {
////                1 -> {
////                    binding.tradrVideo.start()
////                }
////
////                2 -> {
////                    binding.tradrVideo.pause()
////                    clickCount = 0
////                }
////            }
////        })
//
//    }
//
//    override fun onPause() {
//        super.onPause()
//        player?.release()
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        player?.release()
//    }

    //    private fun getAboutUsData() {
//        val retrofit: Retrofit? = RetrofitClient.getInstance(false)
//        val request: WebServices? = retrofit?.create(WebServices::class.java)
//        val call: Call<SuccessModel>? = request?.getAboutUsData()
//        call?.enqueue(object : Callback<SuccessModel?> {
//            override fun onResponse(call: Call<SuccessModel?>, response: Response<SuccessModel?>) {
//                if (response.isSuccessful && response.body() != null
//                    && response.body()?.success.equals("success") &&
//                    response.body()?.message.equals("About")
//                ) {
//                    response.body()?.let {
//                        aboutList.addAll(it.About)
//                    }
//                    println("about Response+++++++++++++++++>${response.body()}")
//                    val imageViewerAdapter = TradrBannerSliderAdapter(aboutList)
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
//                        var currentPageMiddle = 0
//                        if (currentPageMiddle === (aboutList.size + 1) - 1) {
//                            currentPageMiddle = 0
//                        }
//                        binding.imageViewPager.setCurrentItem(currentPageMiddle++, true)
//                    }
//                    timer2Middle = Timer()
//
//                    val DELAY_MS = 1500
//                    val PERIOD_MS = 200
//                }
//            }
//
//            override fun onFailure(call: Call<SuccessModel?>, t: Throwable) {
//                t.printStackTrace()
//            }
//
//        })
//    }

//    private fun getAboutUsData() {
//        val retrofit: Retrofit? = RetrofitClient.getInstance(false)
//        val request: WebServices? = retrofit?.create(WebServices::class.java)
//        val call: Call<SuccessModel>? = request?.getAboutUsData()
//        call?.enqueue(object : Callback<SuccessModel?> {
//            override fun onResponse(call: Call<SuccessModel?>, response: Response<SuccessModel?>) {
//                if (response.isSuccessful && response.body() != null
//                    && response.body()?.success.equals("success") &&
//                    response.body()?.message.equals("About")
//                ) {
//                    response.body()?.let {
//                        aboutList.addAll(it.About)
//                    }
//
//                    if (aboutList == null || aboutList.isEmpty()) {
//                    } else {
//                        val aboutObj = aboutList.get(0)
//                        println("aboutObj==== $aboutObj")
//                        Glide.with(binding.imgBanner.context)
//                            .load(aboutObj.about_banner)
//                            .apply(RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL))
//                            .into(binding.imgBanner)
//                    }
//                }
//            }
//
//            override fun onFailure(call: Call<SuccessModel?>, t: Throwable) {
//                t.printStackTrace()
//            }
//
//        })
//    }

//    private fun setTablayoutItems() {
//
//        binding.tabLayout.tabGravity = TabLayout.GRAVITY_FILL
//        val adapter = TabAdapter(childFragmentManager, lifecycle)
//        binding.viewPager.adapter = adapter
//        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
////            tab.text = stringArrayList[position]
//            when (position) {
//                0 -> {
//                    val tab1: View = layoutInflater.inflate(R.layout.custom_tab_view, null)
//                    val tab1Icon = tab1.findViewById<ImageView>(R.id.tab_icon)
//                    val tab1Text = tab1.findViewById<TextView>(R.id.tab_text)
////                    tab1Icon.setImageDrawable(
////                        ContextCompat.getDrawable(
////                            requireActivity(),
////                            R.drawable.about_us
////                        )
////                    )
//                    tab1Icon.setImageDrawable(
//                        ContextCompat.getDrawable(
//                            requireActivity(),
//                            R.drawable.gallery
//                        )
//                    )
//                    tab1Text.text = "Gallery"
//                    tab.customView = tab1
//                }
//
//                1 -> {
//                    val tab2: View = layoutInflater.inflate(R.layout.custom_tab_view, null)
//                    val tab2Icon = tab2.findViewById<ImageView>(R.id.tab_icon)
//                    val tab2Text = tab2.findViewById<TextView>(R.id.tab_text)
////                    tab2Icon.setImageDrawable(
////                        ContextCompat.getDrawable(
////                            requireActivity(),
////                            R.drawable.qa
////                        )
////                    )
//                    tab2Icon.setImageDrawable(
//                        ContextCompat.getDrawable(
//                            requireActivity(),
//                            R.drawable.discussion
//                        )
//                    )
//                    tab2Text.text = "QNA"
//                    tab.customView = tab2
//                }
//
//                2 -> {
//                    val tab3: View = layoutInflater.inflate(R.layout.custom_tab_view, null)
//                    val tab3Icon = tab3.findViewById<ImageView>(R.id.tab_icon)
//                    val tab3Text = tab3.findViewById<TextView>(R.id.tab_text)
////                    tab3Icon.setImageDrawable(
////                        ContextCompat.getDrawable(
////                            requireActivity(),
////                            R.drawable.strategy
////                        )
////                    )
//                    tab3Icon.setImageDrawable(
//                        ContextCompat.getDrawable(
//                            requireActivity(),
//                            R.drawable.online_course
//                        )
//                    )
//                    tab3Text.text = "Strategic Video"
//                    tab.customView = tab3
//                }
//            }
//        }.attach()
//
////        binding.tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
////            override fun onTabSelected(tab: TabLayout.Tab) {
////
////                when (tab.position) {
////                    0 -> {
////
////                        binding.appBar.logo.visibility = View.VISIBLE
////                        binding.appBar.screenName.visibility = View.VISIBLE
////                        binding.appBar.screenName.text = getString(R.string.about_us)
////                        (activity as MainActivity).updateAppBarTitle(getString(R.string.about_us))
////                    }
////
////                    1 -> {
////                        (activity as MainActivity).updateAppBarTitle(getString(R.string.qna))
////
////                        binding.appBar.logo.visibility = View.VISIBLE
////                        binding.appBar.screenName.visibility = View.VISIBLE
////                        binding.appBar.screenName.text = getString(R.string.qna)
////
////                    }
////
////                    2 -> {
////                        (activity as MainActivity).updateAppBarTitle(getString(R.string.strategic))
////                        binding.appBar.logo.visibility = View.VISIBLE
////                        binding.appBar.screenName.visibility = View.VISIBLE
////                        binding.appBar.screenName.text = getString(R.string.strategic)
////                    }
////                }
////            }
////
////            override fun onTabUnselected(tab: TabLayout.Tab) {}
////            override fun onTabReselected(tab: TabLayout.Tab) {}
////        })
//    }
}