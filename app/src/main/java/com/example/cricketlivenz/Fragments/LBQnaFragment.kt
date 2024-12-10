//package com.likesby.tradr.Fragments
//
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.fragment.app.Fragment
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.ensivo.tradr.Model.SuccessModel
//import com.ensivo.tradr.databinding.FragmentLBQnaBinding
//import com.likesby.tradr.Adapter.QNAdapter
//import com.likesby.tradr.Model.QNA
//import com.likesby.tradr.NetworkLayer.RetrofitClient
//import com.likesby.tradr.NetworkLayer.WebServices
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//import retrofit2.Retrofit
//
//class LBQnaFragment : Fragment() {
//    private lateinit var binding: FragmentLBQnaBinding
//    private lateinit var linearLayoutManager: LinearLayoutManager
//
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View {
//        binding = FragmentLBQnaBinding.inflate(inflater, container, false)
//        linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
//        binding.recyclerData.layoutManager = linearLayoutManager
//        getQNAList()
//        return binding.root
//    }
//
//    private fun getQNAList(){
//        val retrofit: Retrofit? = RetrofitClient.getInstance(false)
//        val request: WebServices? = retrofit?.create(WebServices::class.java)
//        val call: Call<SuccessModel>? = request?.getQNAList("Logic Based Questions")
//        call?.enqueue(object : Callback<SuccessModel?> {
//            override fun onResponse(call: Call<SuccessModel?>, response: Response<SuccessModel?>) {
//                if (response.isSuccessful && response.body() != null
//                    && response.body()?.success.equals("success") &&
//                    response.body()?.message.equals("QNAs")
//                ) {
//                    var qnaList = ArrayList<QNA>()
//                    response.body()?.let {
//                        qnaList.addAll(it.QNA)
//                    }
//
//                    if (qnaList == null || qnaList.isEmpty()){}else{
//                        var fAQAdapter = QNAdapter(qnaList)
//                        binding.recyclerData.adapter = fAQAdapter
//                    }
//                }
//            }
//
//            override fun onFailure(call: Call<SuccessModel?>, t: Throwable) {t.printStackTrace()}
//
//        })
//    }
//}