//package com.likesby.tradr.Fragments
//
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.Toast
//import androidx.fragment.app.Fragment
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.ensivo.tradr.Model.SuccessModel
//import com.ensivo.tradr.databinding.FragmentStrategyVideoBinding
//import com.likesby.tradr.Adapter.StrategicVideoAdapter
//import com.likesby.tradr.Model.StrategicVideos
//import com.likesby.tradr.NetworkLayer.RetrofitClient
//import com.likesby.tradr.NetworkLayer.WebServices
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//import retrofit2.Retrofit
//
//class StrategyVideoFragment : Fragment() {
//    private lateinit var binding: FragmentStrategyVideoBinding
//    private lateinit var linearLayoutManager: LinearLayoutManager
//
//    private var isClickable = 0
//
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View {
//        binding = FragmentStrategyVideoBinding.inflate(inflater, container, false)
//        linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
//        getStrategicVideoList()
//
//        return binding.root
//    }
//
//    private fun getStrategicVideoList(){
//        val retrofit: Retrofit? = RetrofitClient.getInstance(false)
//        val request: WebServices? = retrofit?.create(WebServices::class.java)
//        val call: Call<SuccessModel>? = request?.getStrategicVideoList()
//        call?.enqueue(object : Callback<SuccessModel?> {
//            override fun onResponse(call: Call<SuccessModel?>, response: Response<SuccessModel?>) {
//                if (response.isSuccessful && response.body() != null
//                    && response.body()?.success.equals("success") &&
//                    response.body()?.message.equals("Strategic Videos")
//                ) {
//                    var strategicVideosList = ArrayList<StrategicVideos>()
//                    response.body()?.let {
//                        strategicVideosList.addAll(it.StrategicVideos)
//                    }
//
//                    if (strategicVideosList == null || strategicVideosList.isEmpty()){}else{
//                        var strategicVideoAdapter = StrategicVideoAdapter(strategicVideosList)
//                        binding.recyclerData.layoutManager = linearLayoutManager
//                        binding.recyclerData.adapter = strategicVideoAdapter
//                    }
//                }else if (response.body()?.success.equals("failure")) {
//                    Toast.makeText(context, "Data not available!", Toast.LENGTH_LONG).show()
//                }
//            }
//
//            override fun onFailure(call: Call<SuccessModel?>, t: Throwable) {
//                t.printStackTrace()
//                Toast.makeText(context, "Data not available!", Toast.LENGTH_LONG).show()
//            }
//        })
//    }
//}