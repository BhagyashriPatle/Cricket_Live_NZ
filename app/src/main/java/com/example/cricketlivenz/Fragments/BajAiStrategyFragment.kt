//package com.example.cricketlivenz.Fragments
//
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.fragment.app.Fragment
//import com.bumptech.glide.Glide
//import com.bumptech.glide.load.engine.DiskCacheStrategy
//import com.ensivo.tradr.Model.SuccessModel
//import com.ensivo.tradr.R
//import com.ensivo.tradr.databinding.FragmentBajAiStrategyBinding
//import com.google.android.exoplayer2.MediaItem
//import com.google.android.exoplayer2.SimpleExoPlayer
//import com.google.android.exoplayer2.ui.PlayerView
//import com.example.cricketlivenz.Fragments.StrategyFragment
//import com.likesby.tradr.NetworkLayer.RetrofitClient
//import com.likesby.tradr.NetworkLayer.WebServices
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//import retrofit2.Retrofit
//
///**
// * A simple [Fragment] subclass.
// * Use the [BajAiStrategyFragment.newInstance] factory method to
// * create an instance of this fragment.
// */
//class BajAiStrategyFragment : Fragment() {
//    private lateinit var binding: FragmentBajAiStrategyBinding
//    var player: SimpleExoPlayer? = null
//    private lateinit var playerView: PlayerView
//    private var onClickListener: OnClickListener? = null
//
//    fun setOnClickListener(onClickListener: OnClickListener) {
//        println("onClickListener----$onClickListener")
//        this.onClickListener = onClickListener
//        println("onClickListener = ${this.onClickListener}")
//    }
//
//    interface OnClickListener {
//        fun onClickVideo(videoClick: Boolean)
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        // Inflate the layout for this fragment
//        binding = FragmentBajAiStrategyBinding.inflate(inflater, container, false)
//        getStrategicVideo()
//
//
//
//
//        return binding.root
//    }
//
////    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
////        super.setUserVisibleHint(isVisibleToUser)
////        if (this.isVisible){
////            if (!isVisibleToUser){
////                player?.pause()
////            }
////            if (isVisibleToUser){
////                player?.play()
////            }
////        }
////    }
//
//    private fun getStrategicVideo() {
//
//        val retrofit: Retrofit? = RetrofitClient.getInstance(false)
//        val request: WebServices? = retrofit?.create(WebServices::class.java)
//        val call: Call<SuccessModel> = request!!.getVideoList("Baj AI Strategy")
//        call.enqueue(object : Callback<SuccessModel> {
//            override fun onResponse(call: Call<SuccessModel>, response: Response<SuccessModel>) {
//                if (response.isSuccessful) {
//                    val successResponse: SuccessModel? = response.body()
//                    successResponse?.let {
//                        if (it.Videos.isNotEmpty()) {
//                            val video = it.Videos[0]
//
//                            if (video.video_category == "Baj AI Strategy") {
//                                println("Video Category: ${video.video_category} ${video.video}")
//                                playerView = binding.bajAiVideo
//                                val videoPath = video.video
//                                val uri = video.video
//                                if (uri != null) {
//
//                                    Glide.with(binding.videoThumbnail.context).load(uri)
//                                        .placeholder(R.drawable.dummy_v_t)
//                                        .diskCacheStrategy(
//                                            DiskCacheStrategy.DATA
//                                        ).centerCrop().into(binding.videoThumbnail)
//                                }
//
//                                binding.frmPlayview.setOnClickListener {
//                                    binding.mediaRlt.visibility = View.GONE
//                                    binding.bajAiVideo.visibility = View.VISIBLE
//
//                                    playerView = binding.bajAiVideo
//                                    val videoPath = video.video
//
//                                    val mediaItem = videoPath?.let { MediaItem.fromUri(it) }
//                                    player =
//                                        SimpleExoPlayer.Builder(binding.bajAiVideo.context).build()
//                                    playerView.player = player
//                                    if (mediaItem != null) {
//                                        player?.setMediaItem(mediaItem)
//                                    }
//                                    player?.prepare()
//                                    player?.play()
//
//                                    val strategyFragment = parentFragment as StrategyFragment
//                                    if (strategyFragment != null) {
//                                        if (strategyFragment.strategicVideoAdapterTwo.player != null) {
//                                            if (strategyFragment.strategicVideoAdapterTwo.player?.isPlaying == true) {
//                                                strategyFragment.strategicVideoAdapterTwo.player?.pause()
//                                            }
//                                        }
//                                    }
//                                    strategyFragment.strategicVideoAdapterTwo.player?.pause()
//
//                                    println("strategyFragment ???????= ${strategyFragment}")
////                                    println("strategyFragmentpppp ==== ${strategyFragment.strategicVideoAdapterTwo.player}")
//
//
////                                    onClickListener?.onClickVideo(true)
////            val intent = Intent(mContext, ViewVideoActivity::class.java)
////            intent.putExtra("videoLink", qnaObj.video)
////            mContext.startActivity(intent)
//                                }
//                                binding.bajAiVideo.setOnClickListener(View.OnClickListener {
////                                    onClickListener?.onClickVideo(true)
//                                    val strategyFragment = parentFragment as StrategyFragment
//                                    if (strategyFragment != null) {
//                                        if (strategyFragment.strategicVideoAdapterTwo.player != null) {
//                                            if (strategyFragment.strategicVideoAdapterTwo.player?.isPlaying == true) {
//                                                strategyFragment.strategicVideoAdapterTwo.player?.pause()
//                                            }
//                                        }
//                                    }
//                                    strategyFragment.strategicVideoAdapterTwo.player?.pause()
//                                })
//                            } else {
//                            }
//                        }
//                    }
//                }
//            }
//
//            override fun onFailure(call: Call<SuccessModel>, t: Throwable) {
//                t.printStackTrace()
//            }
//        })
//    }
//
//
//    override fun onPause() {
//        super.onPause()
//        player?.pause()
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        player?.release()
//    }
//
//
////    override fun onStart() {
////        super.onStart()
////        player?.play()
////    }
//
//
//}