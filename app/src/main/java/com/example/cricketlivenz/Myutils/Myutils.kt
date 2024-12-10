//package com.ensivo.tradr.Myutils
//
//import android.app.Activity
//import android.app.AlertDialog
//import android.content.Context
//import android.net.ConnectivityManager
//import android.net.NetworkInfo
//import android.widget.Toast
//import com.ensivo.tradr.R
//import net.gotev.uploadservice.data.UploadNotificationConfig
//import net.gotev.uploadservice.data.UploadNotificationStatusConfig
//import java.util.Locale
//import java.util.regex.Matcher
//import java.util.regex.Pattern
//
//
//class Myutils {
//
//    companion object {
//
//        fun String.notifications(): UploadNotificationConfig {
//
//            return UploadNotificationConfig(
//                notificationChannelId = "UploadServiceDemoChannel",
//                isRingToneEnabled = false,
//                progress = UploadNotificationStatusConfig(
//                    title = "${net.gotev.uploadservice.placeholders.Placeholder.Progress} Percent",
//                    message = "${net.gotev.uploadservice.placeholders.Placeholder.TotalFiles} Files",
//                    clearOnAction = true,
//                    autoClear = true
//
//                ),
//                success = UploadNotificationStatusConfig(
//                    title = "success",
//                    message = "some success message",
//                    clearOnAction = true,
//                    autoClear = true
//                ),
//                error = UploadNotificationStatusConfig(
//                    title = "error",
//                    message = "Something Went Wrong",
//                    iconResourceID = R.drawable.logotradr_new
//                ),
//                cancelled = UploadNotificationStatusConfig(
//                    title = "cancelled",
//                    message = "some cancelled message",
//                    clearOnAction = true,
//                    autoClear = true
//                )
//            )
//
//        }
//
//        infix fun <T> Boolean.then(first: T): T? = if (this) first else null
//
//        fun String.capitalizeWords(): String = split(" ").map {
//            it.replaceFirstChar {
//                if (it.isLowerCase()) it.titlecase(
//                    Locale.getDefault()
//                ) else it.toString()
//            }
//        }.joinToString(" ")
//
//        fun createToast(context: Context?, value: String) {
//            Toast.makeText(context, "" + value, Toast.LENGTH_SHORT).show()
//        }
//
//        fun isConnectingToInternet(context: Context): Boolean {
//            val connectivity =
//                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//            if (connectivity != null) {
//                val info = connectivity.allNetworkInfo
//                if (info != null) for (i in info.indices) if (info[i].state == NetworkInfo.State.CONNECTED) {
//                    return true
//                }
//            }
//            return false
//        }
//
//        fun checkEmail(email: String?): Boolean {
//            if (email == null) {
//                return false
//            }
//            val pattern: Pattern
//            val emailPattern =
//                "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
//            pattern = Pattern.compile(emailPattern)
//            val matcher: Matcher = pattern.matcher(email)
//            return matcher.matches()
//        }
//
//        fun validatePhoneNumber(contact: String?): Boolean {
//            if (contact == null) {
//                return false
//            }
//            val pattern: Pattern
//            val phonePattern = "^[0-9]{10}$"
//            pattern = Pattern.compile(phonePattern)
//            val matcher: Matcher = pattern.matcher(contact)
//            return matcher.matches()
//        }
//
//    }
//
//    object AndroidUtils {
//
//        private var density = 1f
//
//        fun dp(value: Float, context: Context): Int {
//            if (density == 1f) {
//                checkDisplaySize(context)
//            }
//            return if (value == 0f) {
//                0
//            } else Math.ceil((density * value).toDouble()).toInt()
//        }
//
//
//        private fun checkDisplaySize(context: Context) {
//            try {
//                density = context.resources.displayMetrics.density
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//
//        }
//    }
//}