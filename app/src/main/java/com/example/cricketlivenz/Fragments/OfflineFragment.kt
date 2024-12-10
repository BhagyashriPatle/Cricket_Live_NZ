//package com.likesby.tradr.Fragments
//
//import android.os.Bundle
//import androidx.fragment.app.Fragment
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.ensivo.tradr.Model.SuccessModel
//import com.ensivo.tradr.databinding.FragmentOfflineBinding
//import com.likesby.tradr.Adapter.FAQAdapter
//import com.likesby.tradr.Adapter.StrategicVideoAdapterTwo
//import com.likesby.tradr.Model.FAQ
//import com.likesby.tradr.Model.Videos
//import com.likesby.tradr.NetworkLayer.RetrofitClient
//import com.likesby.tradr.NetworkLayer.WebServices
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//import retrofit2.Retrofit
//import java.util.ArrayList
//
//class OfflineFragment : Fragment() {
//    private lateinit var binding: FragmentOfflineBinding
//    private lateinit var linearLayoutManager: LinearLayoutManager
//    private lateinit var linearLayoutManager2: LinearLayoutManager
//
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
//        binding = FragmentOfflineBinding.inflate(inflater, container, false)
//        linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
//        linearLayoutManager2 = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
//        binding.recyclerNote.layoutManager = linearLayoutManager
//        getFAQsList()
//        return binding.root
//    }
//
//    private fun getFAQsList(){
//        val retrofit: Retrofit? = RetrofitClient.getInstance(false)
//        val request: WebServices? = retrofit?.create(WebServices::class.java)
//        val call: Call<SuccessModel>? = request?.getFAQsList("Offline")
//        call?.enqueue(object : Callback<SuccessModel?> {
//            override fun onResponse(
//                call: Call<SuccessModel?>,
//                response: Response<SuccessModel?>
//            ) {
//                if (response.isSuccessful && response.body() != null
//                    && response.body()?.success.equals("success")
//                ) {
//                    var faqList = ArrayList<FAQ>()
//                    response.body()?.let {
//                        faqList.addAll(it.FAQ)
//                    }
//
//                    if (faqList == null || faqList.isEmpty()){}else{
//                        var fAQAdapter = FAQAdapter(faqList)
//                        binding.recyclerNote.adapter = fAQAdapter
//                    }
//
//                    var videosList = ArrayList<Videos>()
//                    response.body()?.let {
//                        videosList.addAll(it.Videos)
//                    }
//
//                    if (videosList == null || videosList.isEmpty()){}else{
//                        var fAQAdapter = StrategicVideoAdapterTwo(videosList)
//                        binding.recyclerData.layoutManager = linearLayoutManager2
//                        binding.recyclerData.adapter = fAQAdapter
//                    }
//
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
//
//}