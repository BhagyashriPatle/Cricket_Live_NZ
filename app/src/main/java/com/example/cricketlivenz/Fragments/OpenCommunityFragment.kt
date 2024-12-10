//package com.likesby.tradr.Fragments
//
//import android.Manifest
//import android.app.Dialog
//import android.content.Context
//import android.content.Intent
//import android.net.Uri
//import android.os.Build
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ImageView
//import android.widget.LinearLayout
//import android.widget.TextView
//import android.widget.Toast
//import androidx.activity.result.ActivityResultLauncher
//import androidx.activity.result.PickVisualMediaRequest
//import androidx.activity.result.contract.ActivityResultContracts
//import androidx.fragment.app.Fragment
//import androidx.fragment.app.FragmentActivity
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.bumptech.glide.Glide
//import com.bumptech.glide.load.engine.DiskCacheStrategy
//import com.bumptech.glide.request.RequestOptions
//import com.ensivo.tradr.Model.SuccessModel
//import com.ensivo.tradr.Myutils.MySharedPreferences
//import com.ensivo.tradr.Myutils.Myutils.Companion.notifications
//import com.ensivo.tradr.R
//import com.ensivo.tradr.databinding.FragmentOpenCommunityBinding
//import com.likesby.tradr.Activity.EditImageActivity
//import com.likesby.tradr.Activity.ViewImageActivity
//import com.likesby.tradr.Activity.ViewVideoActivity
//import com.likesby.tradr.Adapter.AdminChatAdapter
//import com.likesby.tradr.Model.CommunitiesModel
//import com.likesby.tradr.Myutils.FileUtils
//import com.likesby.tradr.NetworkLayer.RetrofitClient
//import com.likesby.tradr.NetworkLayer.ServerConnect
//import com.likesby.tradr.NetworkLayer.WebServices
//import com.theartofdev.edmodo.cropper.CropImage
//import gun0912.tedimagepicker.builder.TedImagePicker
//import net.gotev.uploadservice.data.UploadInfo
//import net.gotev.uploadservice.network.ServerResponse
//import net.gotev.uploadservice.observer.request.RequestObserverDelegate
//import net.gotev.uploadservice.protocols.multipart.MultipartUploadRequest
//import okhttp3.Credentials
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//import retrofit2.Retrofit
//import java.io.File
//import java.sql.DriverManager
//
//class OpenCommunityFragment : Fragment() {
//    private lateinit var binding: FragmentOpenCommunityBinding
//    private lateinit var fragmentActivity: FragmentActivity
//    private lateinit var mySharedPreferences: MySharedPreferences
//    private var communityAdmin = ArrayList<CommunitiesModel>()
//    private var openCommunity = ArrayList<CommunitiesModel>()
//    private lateinit var adminChatAdapter: AdminChatAdapter
//    private lateinit var linearLayoutManagerAdmin: LinearLayoutManager
//    private val CAMERA_REQUEST = 100
//    private lateinit var cameraPermission: Array<String>
//    var imagesListFile: java.util.ArrayList<File>? = arrayListOf<File>()
//    lateinit var singleVideoLauncher: ActivityResultLauncher<PickVisualMediaRequest>
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        binding = FragmentOpenCommunityBinding.inflate(inflater, container, false)
//        mySharedPreferences = MySharedPreferences.getInstance(context)!!
//        fragmentActivity = context as FragmentActivity
//        var role = mySharedPreferences.userObjectModel!!.role
//        var name = mySharedPreferences.userObjectModel!!.name
//        //  if (role == "Admin" || name == "admin"){
//        if (role == "Admin") {
//            binding.bottomBar.visibility = View.GONE
//            binding.bottomBarChat.visibility = View.VISIBLE
//        } else {
//            binding.bottomBar.visibility = View.VISIBLE
//            binding.bottomBarChat.visibility = View.GONE
//        }
//
//        cameraPermission =
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) { //Android v13 and later
//                arrayOf(
//                    Manifest.permission.READ_MEDIA_IMAGES,
//                    Manifest.permission.READ_MEDIA_VIDEO,
//                    Manifest.permission.CAMERA
//                )
//            } else {
//                arrayOf(
//                    Manifest.permission.CAMERA,
//                    Manifest.permission.READ_EXTERNAL_STORAGE,
//                    Manifest.permission.WRITE_EXTERNAL_STORAGE
//                )
//            }
//        getAdminCommunityMessages()
//        initClicks()
//        return binding.root
//    }
//
//    private fun selectionOption() {
//        val dialog = Dialog(fragmentActivity, R.style.MaterialDialogSheet)
//        dialog.setContentView(R.layout.dialog_selection_layout)
//        val view = layoutInflater.inflate(R.layout.dialog_selection_layout, null)
//        val buttonImage = view.findViewById<TextView>(R.id.btnYes)
//        val txtGearType = view.findViewById<TextView>(R.id.txtGearType)
//        dialog.setContentView(view)
//        buttonImage.setOnClickListener {
//            dialog.dismiss()
//            requestCameraPermission()
//        }
//        val buttonVideo = view.findViewById<TextView>(R.id.btnNo)
//        buttonVideo.setOnClickListener {
//            dialog.dismiss()
//            singleVideoLauncher.launch(
//                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.VideoOnly)
//            )
//        }
//        dialog.setCancelable(true)
//        dialog.window?.setLayout(
//            LinearLayout.LayoutParams.WRAP_CONTENT,
//            LinearLayout.LayoutParams.WRAP_CONTENT
//        )
//        dialog.show()
//    }
//
//    private fun sureUploadvideo(uri: Uri) {
//        val dialog = Dialog(fragmentActivity, R.style.MaterialDialogSheet)
//        dialog.setContentView(R.layout.dialog_logout_layout)
//        val view = layoutInflater.inflate(R.layout.dialog_logout_layout, null)
//        val button = view.findViewById<TextView>(R.id.btnYes)
//        val imgDelete = view.findViewById<ImageView>(R.id.imgDelete)
//        Glide.with(fragmentActivity)
//            .load(uri)
//            .error(R.drawable.ic_user_dp)
//            .placeholder(R.drawable.ic_user_dp)
//            .apply(
//                RequestOptions()
//                    .diskCacheStrategy(DiskCacheStrategy.ALL)
//            )
//            .into(imgDelete)
//        val txtGearType = view.findViewById<TextView>(R.id.txtGearType)
//        txtGearType.text = "Are you sure to upload this video"
//        dialog.setContentView(view)
//        button.setOnClickListener {
//            dialog.dismiss()
//            saveAdminVideo()
//        }
//        val buttonNo = view.findViewById<TextView>(R.id.btnNo)
//        buttonNo.setOnClickListener {
//            dialog.dismiss()
//        }
//        dialog.setCancelable(true)
//        dialog.window?.setLayout(
//            LinearLayout.LayoutParams.WRAP_CONTENT,
//            LinearLayout.LayoutParams.WRAP_CONTENT
//        )
//        dialog.show()
//    }
//
//    private fun initClicks() {
//        singleVideoLauncher =
//            registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
//                if (uri != null) {
//                    imagesListFile?.clear()
//                    var image = FileUtils.getPath(requireContext(), uri)
//                    var imageFile = File(image)
//                    imagesListFile?.add(imageFile)
//                    sureUploadvideo(uri)
//                } else {
//
//                }
//
//            }
//        binding.buttonGchatSend.setOnClickListener(View.OnClickListener {
//            if (binding.editGchatMessages.text.toString() == "") {
//                Toast.makeText(context, "Please Type Something", Toast.LENGTH_SHORT)
//                    .show()
//            } else {
//                var user_id = mySharedPreferences.userObjectModel?.user_id
//                var message = binding.editGchatMessages.text.toString()
//                binding.editGchatMessages.setText("")
//                binding.editGchatMessages.minLines = 1
//                val communitiesModel = CommunitiesModel(
//                    "", "", "", user_id!!,
//                    "", "", mySharedPreferences.userObjectModel?.name!!, message, "", ""
//                )
//                communityAdmin.add(communitiesModel)
//                adminChatAdapter.notifyDataSetChanged()
//                binding.recyclerAdminChat.scrollToPosition(communityAdmin.size - 1)
//                linearLayoutManagerAdmin.stackFromEnd = true
//                binding.recyclerAdminChat.itemAnimator = null
//                sendMessageTocommunity(message)
//            }
//        })
//
////        binding.editGchatMessages.setOnTouchListener(object : View.OnTouchListener {
////            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
////                if (event?.action == MotionEvent.ACTION_UP) {
////                    if (event.getRawX() >= binding.editGchatMessages.right -
////                        binding.editGchatMessages.compoundDrawables[2].bounds.width()) {
////                        requestCameraPermission()
////                        return true
////                    }
////                }
////                return false
////            }
////        })
//
////        binding.imgAttachVideo.setOnClickListener() {
//////            singleVideoLauncher.launch(
//////                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.VideoOnly)
//////            )
//////            selectionOption()
////        }
//        binding.imgAttachImage.setOnClickListener {//            requestCameraPermission()
//            selectionOption()
//        }
//    }
//
//    private fun saveAdminVideo() {
//        binding.progressBar.progressBar.visibility = View.VISIBLE
//        val multipartUploadRequest = MultipartUploadRequest(
//            requireContext(), ServerConnect.BASE_URL + "CommunityMessageChat"
//        )
//        multipartUploadRequest.addHeader(
//            "Authorization",
//            Credentials.basic("tradr", "tradrc1b9fe16e7d1e9c84ccb218fa7bf69")
//        )
//            .addHeader("X-API-KEY", "lxZhw9eI0bm3XWjiwcjiZjT7DpkdpkZjajAvT33qMAY=")
//            .addParameter("message", "")
//            .addParameter("user_id", mySharedPreferences.userObjectModel?.user_id.toString())
//            .setAutoDeleteFilesAfterSuccessfulUpload(false)
//            .setUsesFixedLengthStreamingMode(true)
//            .setNotificationConfig { context, uploadId -> "adjust".notifications() }
//            .setMaxRetries(4)
//            .setMethod("POST")
//        for (i in 0 until imagesListFile!!.size) {
//            multipartUploadRequest.addFileToUpload(
//                imagesListFile!!.get(i).path.toString(),
//                "video",
//                imagesListFile!!.get(i).path,
//                "UTF-8"
//            )
//        }
//
//        multipartUploadRequest.subscribe(requireContext(), this,
//            object : RequestObserverDelegate {
//                override fun onCompleted(context: Context, uploadInfo: UploadInfo) {
////                finish()
//                }
//
//                override fun onCompletedWhileNotObserving() {
//                }
//
//                override fun onError(
//                    context: Context,
//                    uploadInfo: UploadInfo,
//                    exception: Throwable
//                ) {
//                }
//
//                override fun onProgress(context: Context, uploadInfo: UploadInfo) {
//                }
//
//                override fun onSuccess(
//                    context: Context,
//                    uploadInfo: UploadInfo,
//                    serverResponse: ServerResponse
//                ) {
//                    DriverManager.println("upload successs-------")
//                    imagesListFile!!.clear()
//                    binding.progressBar.progressBar.visibility = View.GONE
//                    getAdminCommunityMessages()
//                }
//            })
//    }
//
//    private fun requestCameraPermission() {
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            requestPermissions(cameraPermission, CAMERA_REQUEST)
//        }
//
//
////        if (checkPermission()) {
////            println("aaaaaaaaaaaaaaaeeeeeeeeeeeeeeeeeeeeeeeee")
////            TedImagePicker.with(requireContext())
////                .start { uri ->
////                    CropImage.activity(uri).start(fragmentActivity, this);
////                }
////        } else {
////            println("1222222222222222222222222222222222")
////            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
////                requestPermissions(cameraPermission, CAMERA_REQUEST)
////            }
////        }
//    }
//
//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray
//    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        if (grantResults.size > 0) {
//            TedImagePicker.with(requireContext())
//                .start { uri ->
//                    CropImage.activity(uri).start(fragmentActivity, this)
//                }
//        }
//    }
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
//            if (data != null) {
//                val result = CropImage.getActivityResult(data)
//                var imageUri = result.uri
//                val intent = Intent(fragmentActivity, EditImageActivity::class.java)
//                intent.putExtra("cropImageUri", imageUri.toString())
//                intent.putExtra("community", "Admin")
//                startActivity(intent)
//            } else {
//
//            }
//        }
//    }
//
//    /// if (resultCode === CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
////            val result = CropImage.getActivityResult(data)
////            val error = result.error
////        }
//    private fun sendMessageTocommunity(message: String) {
//        var user_id = mySharedPreferences.userObjectModel?.user_id
//        val retrofit: Retrofit? = RetrofitClient.getInstance(false)
//        val request: WebServices? = retrofit?.create(WebServices::class.java)
//        val call: Call<SuccessModel>? =
//            request?.sendAdminChat(user_id, mySharedPreferences.userObjectModel?.name, message)
//
//        call?.enqueue(object : Callback<SuccessModel?> {
//            override fun onResponse(
//                call: Call<SuccessModel?>,
//                response: Response<SuccessModel?>
//            ) {
//                if (response.isSuccessful && response.body() != null
//                    && response.body()?.success.equals("success") &&
//                    response.body()?.message.equals("chatting Suceessfully")
//                ) {
//                    getAdminCommunityMessages()
//                } else if (response.body()?.success.equals("failure")) {
//                    Toast.makeText(context, "Something Went Wrong", Toast.LENGTH_LONG).show()
//                }
//            }
//
//            override fun onFailure(call: Call<SuccessModel?>, t: Throwable) {
//                t.printStackTrace()
//            }
//        })
//    }
//
//    private fun getAdminCommunityMessages() {
//        binding.progressBar.progressBar.visibility = View.VISIBLE
//
//        var user_id = mySharedPreferences.userObjectModel?.user_id
//        val retrofit: Retrofit? = RetrofitClient.getInstance(false)
//        val request: WebServices? = retrofit?.create(WebServices::class.java)
//        val call: Call<SuccessModel>? = request?.getCommunityMessages(user_id)
//        call?.enqueue(object : Callback<SuccessModel?> {
//            override fun onResponse(
//                call: Call<SuccessModel?>,
//                response: Response<SuccessModel?>
//            ) {
//                if (response.isSuccessful && response.body() != null
//                    && response.body()?.success.equals("success") &&
//                    response.body()?.message.equals("Community Messages")
//                ) {
//                    binding.progressBar.progressBar.visibility = View.GONE
//
//                    communityAdmin.clear()
//                    response.body()?.let {
//                        communityAdmin.addAll(it.CommunityMessage)
//                    }
//
//                    linearLayoutManagerAdmin = LinearLayoutManager(
//                        fragmentActivity,
//                        LinearLayoutManager.VERTICAL,
//                        false
//                    )
//                    binding.recyclerAdminChat.layoutManager = linearLayoutManagerAdmin
//                    adminChatAdapter = AdminChatAdapter(communityAdmin)
//                    binding.recyclerAdminChat.adapter = adminChatAdapter
//
//                    binding.recyclerAdminChat.scrollToPosition(communityAdmin.size)
//                    adminChatAdapter.setOnClickListener(object :
//                        AdminChatAdapter.OnClickListener {
//                        override fun onClickImage(communityModel: CommunitiesModel) {
//                            println("aaaaaaaaaaa")
////                            chatModel.media = imageUri.toString()
//                            val intent = Intent(fragmentActivity, ViewImageActivity::class.java)
//                            intent.putExtra("imageLink", communityModel.image)
//                            println("imageLink==========" + communityModel.image)
//                            startActivity(intent)
//                        }
//                    })
//                    adminChatAdapter.setonClickListenerV(object :
//                        AdminChatAdapter.OnClickListenerV {
//                        override fun onClickVideo(communityModel: CommunitiesModel) {
//                            val intent = Intent(fragmentActivity, ViewVideoActivity::class.java)
//                            intent.putExtra("videoLink", communityModel.video)
//                            println("videoLink==========" + communityModel.video)
//                            startActivity(intent)
//                        }
//                    })
//                    linearLayoutManagerAdmin.stackFromEnd = true
//                    binding.recyclerAdminChat.itemAnimator = null
//
//                } else if (response.body()?.success.equals("failure") &&
//                    response.body()?.message.equals("Data not available")
//                ) {
//                    binding.progressBar.progressBar.visibility = View.GONE
//                }
//            }
//
//            override fun onFailure(call: Call<SuccessModel?>, t: Throwable) {
//                t.printStackTrace()
//            }
//        })
//    }
//}