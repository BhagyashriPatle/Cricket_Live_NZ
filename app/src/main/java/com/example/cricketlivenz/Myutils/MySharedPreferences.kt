//package com.ensivo.tradr.Myutils
//
//import android.content.Context
//import android.content.SharedPreferences
//import com.ensivo.tradr.Model.UserInformationModel
//import com.google.gson.Gson
//
//class MySharedPreferences {
//    private val editor: SharedPreferences.Editor
//    fun clearAll() {
//        editor.clear()
//        editor.apply()
//    }
//
//    fun setStringKey(keyname: String?, state: String?) {
//        sharedPrefrences.edit().putString(keyname, state).apply()
//    }
//
//    fun setUserModel(userModel: UserInformationModel?) {
//        val gson = Gson()
//        val s: String = gson.toJson(userModel)
//        setStringKey(userModelKey, s)
//    }
//
//    val userObjectModel: UserInformationModel?
//        get() {
//            val serializeModel = getStringkey(userModelKey)
//            if(serializeModel == null){
//                return null
//            }else{
//                val gson = Gson()
//                return gson.fromJson(serializeModel, UserInformationModel::class.java)
//            }
//
//        }
//
//    fun setLongKey(keyname: String?, state: Long) {
//        sharedPrefrences.edit().putLong(keyname, state).apply()
//    }
//
//    fun getLongKey(keyname: String?): Long {
//        return sharedPrefrences.getLong(keyname, 0)
//    }
//
//    fun getStringkey(keyname: String?): String? {
//        return sharedPrefrences.getString(keyname, null)
//    }
//
//    fun setIntKey(keyname: String?, state: Int) {
//        sharedPrefrences.edit().putInt(keyname, state).apply()
//    }
//
//    fun getIntkey(keyname: String?): Int {
//        return sharedPrefrences.getInt(keyname, 0)
//    }
//
//    companion  object {
//        private const val KEY_PREFERENCE_NAME = "TRADR"
//        const val name = "name"
//        const val userModelKey = "userModel"
//        const val latitude = "latitude"
//        const val longitude = "longitude"
//
//        //instance field
//        lateinit var sharedPrefrences: SharedPreferences
//            private set
//        private var mInstance: MySharedPreferences? = null
//        private var mContext: Context? = null
//        fun removeValueFromKey(keyname: String?) {
//            sharedPrefrences.edit().remove(keyname).apply()
//        }
//
//        fun getInstance(context: Context?): MySharedPreferences? {
//            mContext = context
//            if (mInstance == null) {
//                mInstance = MySharedPreferences()
//            }
//            return mInstance
//        }
//    }
//
//    init {
//        sharedPrefrences =
//            mContext!!.getSharedPreferences(KEY_PREFERENCE_NAME, Context.MODE_PRIVATE)
//        editor = sharedPrefrences.edit()
//    }
//}
