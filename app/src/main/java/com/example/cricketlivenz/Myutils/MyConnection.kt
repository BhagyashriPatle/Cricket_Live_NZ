//package com.ensivo.tradr.Myutils
//
//import android.content.Context
//import android.net.ConnectivityManager
//import android.util.Log
//import com.facebook.FacebookSdk.getApplicationContext
//
//
//class MyConnection {
//
//    fun isConnected(): Boolean {
//        var connected = false
//        try {
//            val cm = getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//            val nInfo = cm.activeNetworkInfo
//            connected = nInfo != null && nInfo.isAvailable && nInfo.isConnected
//            return connected
//        } catch (e: Exception) {
//            Log.e("Connectivity Exception", e.message.toString())
//        }
//        return connected
//    }
//
//    companion object {
//        private var mInstance: MyConnection? = null
//        private var mContext: Context? = null
//
//        fun getInstance(context: Context?): MyConnection? {
//            mContext = context
//            if (mInstance == null) {
//                mInstance = MyConnection()
//            }
//            return mInstance
//        }
//    }
//}