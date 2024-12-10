//package com.likesby.tradr.Fragments
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
//import com.ensivo.tradr.databinding.FragmentGoatBinding
//import com.google.android.exoplayer2.MediaItem
//import com.google.android.exoplayer2.SimpleExoPlayer
//import com.google.android.exoplayer2.ui.PlayerView
//import com.likesby.tradr.NetworkLayer.RetrofitClient
//import com.likesby.tradr.NetworkLayer.WebServices
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//import retrofit2.Retrofit
//
///**
// * A simple [Fragment] subclass.
// * Use the [GoatFragment.newInstance] factory method to
// * create an instance of this fragment.
// */
//class GoatFragment : Fragment() {
//    private lateinit var binding: FragmentGoatBinding
//     var player: SimpleExoPlayer? = null
//    private lateinit var playerView: PlayerView
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        // Inflate the layout for this fragment
//        binding = FragmentGoatBinding.inflate(inflater, container, false)
//        getGoatVideo()
//        return binding.root
//    }
//
//    private fun getGoatVideo() {
//        val retrofit: Retrofit? = RetrofitClient.getInstance(false)
//        val request: WebServices? = retrofit?.create(WebServices::class.java)
//        val call: Call<SuccessModel> = request!!.getVideoList("GOAT Writing")
//        call.enqueue(object : Callback<SuccessModel> {
//            override fun onResponse(call: Call<SuccessModel>, response: Response<SuccessModel>) {
//                if (response.isSuccessful) {
//                    val successResponse: SuccessModel? = response.body()
//                    successResponse?.let {
//                        if (it.Videos.isNotEmpty()) {
//                            val video = it.Videos[0]
//
//                            if (video.video_category == "GOAT Writing") {
//                                println("Video Category GOAT Writing: ${video.video_category} ${video.video}")
//                                playerView = binding.goatVideo
//                                val videoPath = video.video
//                                val uri = video.video
//                                if (uri != null) {
//                                    Glide.with(binding.videoThumbnail.context).load(uri)
//                                        .error(R.drawable.dummy_v_t)
//                                        .diskCacheStrategy(
//                                            DiskCacheStrategy.DATA
//                                        ).centerCrop().into(binding.videoThumbnail)
//                                }
////                                else {
////                                    Glide.with(binding.videoThumbnail.context).asGif().load(R.drawable.loader)
////                                        .diskCacheStrategy(
////                                            DiskCacheStrategy.DATA
////                                        ).fitCenter().into(binding.videoThumbnail)
////                                }
////                                    Glide.with(binding.videoThumbnail.context).load(uri)
////                                        .placeholder(R.drawable.dummy_video)
////                                        .diskCacheStrategy(
////                                            DiskCacheStrategy.DATA
////                                        ).centerCrop().into(binding.videoThumbnail)
////                                }
//
//                                binding.frmPlayview.setOnClickListener {
//                                    binding.mediaRlt.visibility = View.GONE
//                                    binding.goatVideo.visibility = View.VISIBLE
//                                    playerView = binding.goatVideo
//                                    val videoPath = video.video
//
//                                    val mediaItem = videoPath?.let { MediaItem.fromUri(it) }
//                                    player =
//                                        SimpleExoPlayer.Builder(binding.goatVideo.context).build()
//                                    playerView.player = player
//                                    if (mediaItem != null) {
//                                        player?.setMediaItem(mediaItem)
//                                    }
//                                    player?.prepare()
//                                    player?.play()
//                                    parentFragmentCall()
////            val intent = Intent(mContext, ViewVideoActivity::class.java)
////            intent.putExtra("videoLink", qnaObj.video)
////            mContext.startActivity(intent)
//                                }
//                            } else {
//                            }
//                        } else {
////                            binding.notAvailable.visibility=View.GONE
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
//    private fun parentFragmentCall() {
//        val strategyFragment = parentFragment as StrategyFragment
//        if (strategyFragment != null) {
//            if (strategyFragment.strategicVideoAdapterTwo.player != null) {
//                if (strategyFragment.strategicVideoAdapterTwo.player?.isPlaying == true) {
//                    strategyFragment.strategicVideoAdapterTwo.player?.pause()
//                }
//            }
//        }
//        strategyFragment.strategicVideoAdapterTwo.player?.pause()
//    }
//    override fun onPause() {
//        super.onPause()
//
//        player?.pause()
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        player?.release()
//    }
//
//    //
////    override fun onStart() {
////        super.onStart()
////        player?.play()
////    }
//}