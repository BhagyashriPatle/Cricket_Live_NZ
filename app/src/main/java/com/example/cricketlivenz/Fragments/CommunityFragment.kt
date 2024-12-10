//package com.likesby.tradr.Fragments
//
//import android.Manifest
//import android.app.Activity.RESULT_OK
//import android.app.AlertDialog
//import android.content.Context
//import android.content.Intent
//import android.net.Uri
//import android.os.Build
//import android.os.Bundle
//import android.text.Editable
//import android.text.TextWatcher
//import android.view.LayoutInflater
//import android.view.MotionEvent
//import android.view.View
//import android.view.ViewGroup
//import android.view.WindowManager
//import android.view.inputmethod.InputMethodManager
//import android.widget.ImageView
//import android.widget.LinearLayout
//import android.widget.RadioGroup
//import android.widget.Toast
//import androidx.cardview.widget.CardView
//import androidx.core.content.ContextCompat.getSystemService
//import androidx.core.view.isVisible
//import androidx.databinding.DataBindingUtil
//import androidx.fragment.app.Fragment
//import androidx.fragment.app.FragmentActivity
//import androidx.fragment.app.FragmentManager
//import androidx.fragment.app.FragmentTransaction
//import androidx.recyclerview.widget.ItemTouchHelper
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.bumptech.glide.Glide
//import com.bumptech.glide.load.engine.DiskCacheStrategy
//import com.bumptech.glide.request.RequestOptions
//import com.ensivo.tradr.MainActivity
//import com.ensivo.tradr.Model.SuccessModel
//import com.ensivo.tradr.Myutils.MySharedPreferences
//import com.ensivo.tradr.Myutils.Myutils.Companion.notifications
//import com.ensivo.tradr.R
//import com.ensivo.tradr.databinding.ActivityCommunityBinding
//import com.likesby.tradr.Activity.EditImageActivity
//import com.likesby.tradr.Activity.ProfileViews.ProfileActivity
//import com.likesby.tradr.Activity.ViewImageActivity
//import com.likesby.tradr.Activity.ViewVideoActivity
//import com.likesby.tradr.Adapter.AdminChatAdapter
//import com.likesby.tradr.Adapter.MyChatAdapter
//import com.likesby.tradr.Model.ChatModel
//import com.likesby.tradr.Model.CommunitiesModel
//import com.likesby.tradr.Myutils.FileUtils
//import com.likesby.tradr.NetworkLayer.RetrofitClient
//import com.likesby.tradr.NetworkLayer.ServerConnect
//import com.likesby.tradr.NetworkLayer.WebServices
//import com.likesby.tradr.Service.NotificationServiceExtension
//import com.likesby.tradr.SwipeControllerActions
//import com.likesby.tradr.SwipeToReplyCallBack
//import com.theartofdev.edmodo.cropper.CropImage
//import gun0912.tedimagepicker.builder.TedImagePicker
//import net.gotev.uploadservice.data.UploadInfo
//import net.gotev.uploadservice.network.ServerResponse
//import net.gotev.uploadservice.observer.request.RequestObserverDelegate
//import net.gotev.uploadservice.protocols.multipart.MultipartUploadRequest
//import okhttp3.Credentials
//
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//import retrofit2.Retrofit
//import java.io.File
//import java.util.Timer
//import java.util.TimerTask
//
//class CommunityFragment : Fragment() {
//    private lateinit var binding:ActivityCommunityBinding
//    private var media = ""
//    private lateinit var mySharedPreferences: MySharedPreferences
//    private var chatList = ArrayList<ChatModel>()
//    private var communityAdmin= ArrayList<CommunitiesModel>()
//    private var communityType = ArrayList<CommunitiesModel>()
//    private var openCommunity = ArrayList<CommunitiesModel>()
//    private var closeCommunity = ArrayList<CommunitiesModel>()
//    private var c1 = " "
//    private var c2 = " "
//    private var isFirstCall: Boolean = true
//    var myTimerNote: Timer? = null
//    private val CAMERA_REQUEST = 100
//    private var PICK_iMAGE_REQUEST_CODE = 101
//    private lateinit var cameraPermission: Array<String>
//    lateinit var storagePermission: Array<String>
//    var textheight = 0
//    private var imageUri: Uri? = null
//    var imageFile: File? = null
//    var imagesListFile: java.util.ArrayList<File>? = arrayListOf<File>()
//    private lateinit var requestAdapter: MyChatAdapter
//    private  lateinit var adminChatAdapter: AdminChatAdapter
//    private lateinit var fragmentActivity: FragmentActivity
//    private var hasBoughtCourse = false
//    private lateinit var linearLayoutManager: LinearLayoutManager
//    private lateinit var linearLayoutManagerAdmin: LinearLayoutManager
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        binding = ActivityCommunityBinding.inflate(inflater, container, false)
//        mySharedPreferences = MySharedPreferences.getInstance(context)!!
//        fragmentActivity = context as FragmentActivity
//        startNotificationTimer()
//        initData()
//        binding.recyclerAdminChat.visibility=View.VISIBLE
//        binding.layoutChatChatbox.visibility = View.VISIBLE
//        binding.layoutChatChatboxs.visibility = View.GONE
//        binding.openLayout.visibility = View.GONE
//
//        if (arguments == null){}else{
//            val FinalImageUri = Uri.parse(requireArguments().getString("finalImage"))
//            var finalText = requireArguments().getString("finalText")
//            binding.toggle.check(R.id.close)
//
//            hasBoughtCourse = true
//            binding.userMsg12.visibility = View.GONE
//            binding.openLayout.visibility = View.VISIBLE
//            binding.recyclerChat.adapter?.notifyDataSetChanged()
//            linearLayoutManager.stackFromEnd = true
//            binding.layoutChatChatbox.visibility = View.GONE
//            binding.layoutChatChatboxs.visibility = View.VISIBLE
//            binding.recyclerAdminChat.visibility=View.GONE
//            binding.close.isEnabled = true
//            binding.editGchatMessages.setText(finalText)
//            var image = FileUtils.getPath(requireContext(), FinalImageUri)
//            imageFile = File(image)
//            imagesListFile?.add(imageFile!!)
//            saveImages()
//        }
//        return binding.root
//    }
//
//    private fun initData() {
////new
//        val messageSwipeController = SwipeToReplyCallBack(requireContext(), object : SwipeControllerActions {
//            override fun showReplyUI(position: Int) {
//                showQuotedMessage(chatList[position].message.toString())
//            }
//        })
//
//        val itemTouchHelper = ItemTouchHelper(messageSwipeController)
//        itemTouchHelper.attachToRecyclerView(binding.recyclerChat)
//
//
//        binding.cancelButton.setOnClickListener {
//            hideReplyLayout()
//        }
//
//
////new
//
//        textheight = binding.editGchatMessages.height
//        println("textheight = ${textheight}")
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
//        linearLayoutManager =
//            LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
//        binding.recyclerChat.layoutManager = linearLayoutManager
//        println("arguments ========= ${arguments}")
//        requestAdapter = MyChatAdapter(chatList, communityType)
//        requestAdapter.setOnClickListener(object : MyChatAdapter.OnClickListener {
//            override fun onClickImage(chatModel: ChatModel) {
//                val intent = Intent(fragmentActivity, ViewImageActivity::class.java)
//                intent.putExtra("imageLink", chatModel.media)
//                startActivity(intent)
//            }
//
//            override fun onClickReply(chatModel: ChatModel, item_view_long: LinearLayout) {
//
//            }
//        })
//        binding.recyclerChat.adapter = requestAdapter
//        getAdminCommunityMessages()
//        getChattingList()
////        saveImages()
////        binding.appBar.logo.visibility = View.GONE
////        binding.appBar.screenName.visibility = View.VISIBLE
////        binding.appBar.screenName.text = getString(R.string.community)
//        binding.toggle.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { group, checkedId ->
//            if (checkedId== R.id.open){
//                updateDataInAdapter()
//            }else if (checkedId == R.id.close){
//                if (!hasBoughtCourse) {
//                    if (arguments == null){
//                        showDialog()
//                    }
//                }
//
//                updateDataInAdapter()
//                getChattingList()
//                setCommunity()
//                println("binding.scrollClose.isVisible =========== ${binding.scrollClose.isVisible}")
//            }
//
//        })
//        binding.buttonGchatSend.setOnClickListener(View.OnClickListener {
//            if (binding.editGchatMessages.text.toString() == "") {
//                //new
//
//                hideReplyLayout()
//                Toast.makeText(context, "Please Type Something", Toast.LENGTH_SHORT)
//                    .show()
//            } else {
//                //new
//
//                hideReplyLayout()
//                var message = binding.editGchatMessages.text.toString()
//                binding.editGchatMessages.setText("")
//                binding.editGchatMessages.minLines = 1
////                sendMessageToFriend(message)
////                saveImages()
//            }
//        })
//        binding.editGchatMessages.setOnTouchListener(object : View.OnTouchListener {
//            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
//                if (event?.action == MotionEvent.ACTION_UP) {
//                    if (event.getRawX() >= binding.editGchatMessages.right - binding.editGchatMessages.compoundDrawables[2].bounds.width()) {
//                        requestCameraPermission()
//                        hideReplyLayout()
////                        openGallery()
////                        Toast.makeText(context, "Coming Soon", Toast.LENGTH_SHORT).show()
//
//                        return true
//                    }
//                }
//                return false
//            }
//        })
//
//        binding.editGchatMessages.addTextChangedListener(object : TextWatcher {
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//            }
//
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//            }
//
//            override fun afterTextChanged(s: Editable?) {
//                println("s ==== ${s}")
//                println("s.toString().length = ${s.toString().length}")
//
//                if (binding.editGchatMessages.getLineCount() == binding.editGchatMessages.getMaxLines()) {
//                    binding.editGchatMessages.setMaxLines((binding.editGchatMessages.getLineCount() + 1));
//                }
//                if (s.toString().length > 100) {
//                    binding.editGchatMessages.height = 500
//                }
//            }
//
//        })
////        val bundle = arguments
////        if (bundle != null) {
////            val notificationPayload = bundle.getString("notificationPayload")
////            binding.userMsg12.text = notificationPayload
////            println("++++++++++++++++++++++++++++++++++++++++++> $notificationPayload")
////        } else {
////            println("Not get arguments")
////        }
//////
//        setProfile()
//    }
////new
//
//    private fun showQuotedMessage(message: String) {
//        binding.editGchatMessages.requestFocus()
////        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
////        inputMethodManager.showSoftInput(binding.editGchatMessages, InputMethodManager.SHOW_IMPLICIT)
//        binding.txtQuotedMsg.setText(message)
//        binding.replyLayout.visibility = View.VISIBLE
//
//    }
//    private fun hideReplyLayout() {
//        binding.replyLayout.visibility  = View.GONE
//    }
//
////new
//
//    private fun saveImages() {
//        binding.progressBar.progressBar.visibility = View.VISIBLE
//        println("aaaaaaaaasdsdsdsfsave")
//        linearLayoutManager.stackFromEnd = true
//        val multipartUploadRequest = MultipartUploadRequest(
//            requireContext(), ServerConnect.BASE_URL + "Chats"
//        )
//        multipartUploadRequest.addHeader(
//            "Authorization",
//            //        credentials = Credentials.basic("tradr", "tradrc1b9fe16e7d1e9c84ccb218fa7bf69");
//            Credentials.basic("tradr", "tradrc1b9fe16e7d1e9c84ccb218fa7bf69")
//        )
//            //                    requestBuilder .header("X-API-KEY", "lxZhw9eI0bm3XWjiwcjiZjT7DpkdpkZjajAvT33qMAY=");
//            .addHeader("X-API-KEY", "lxZhw9eI0bm3XWjiwcjiZjT7DpkdpkZjajAvT33qMAY=")
//            .addParameter("community_id", c1)
//            .addParameter("message", binding.editGchatMessages.text.toString())
//            .addParameter("user_id", mySharedPreferences.userObjectModel?.user_id.toString())
//            .setAutoDeleteFilesAfterSuccessfulUpload(false)
//            .setUsesFixedLengthStreamingMode(true)
//            .setNotificationConfig { context, uploadId -> "adjust".notifications() }
//            .setMaxRetries(4)
//            .setMethod("POST")
//        for (i in 0 until imagesListFile!!.size) {
//            multipartUploadRequest.addFileToUpload(
//                imagesListFile!!.get(i).path.toString(),
//                "media",
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
//                    binding.editGchatMessages.setText("")
//                }
//
//                override fun onSuccess(
//                    context: Context,
//                    uploadInfo: UploadInfo,
//                    serverResponse: ServerResponse
//                ) {
//                    println("upload successs-------")
//                    binding.progressBar.progressBar.visibility = View.GONE
//                    getChattingList()
//                    setCommunity()
//                    binding.editGchatMessages.setText("")
//                    imagesListFile!!.clear()
//
//                }
//            })
//    }
//
//
//    private fun startNotificationTimer() {
//        myTimerNote = Timer()
//
//        myTimerNote!!.scheduleAtFixedRate(object : TimerTask() {
//            override fun run() {
//
//                try {
//                    if (NotificationServiceExtension.osNotification != null) {
//                        val osNotification = NotificationServiceExtension.osNotification
//                        if (osNotification?.additionalData == null || osNotification?.additionalData.length() == 0) {
//                        } else {
//                            var type = osNotification?.additionalData?.getJSONArray("type")
//                            println("type =????????? ${type}")
//                            if (type!!.get(0) == "Message Notification") {
//                                getChattingList()
//                                NotificationServiceExtension.osNotification = null
//                            }
//
//                        }
//                    }
//                } catch (e: Exception) {
//                    e.printStackTrace()
//                }
//            }
//        }, 0, 3000)
//    }
//
////    private fun sendMessageToFriend(message: String) {
////        var user_id = mySharedPreferences.userObjectModel?.user_id
////        val retrofit: Retrofit? = RetrofitClient.getInstance(false)
////        val request: WebServices? = retrofit?.create(WebServices::class.java)
////        val call: Call<SuccessModel>? = request?.sendChat(c1, message, user_id, media)
////        call?.enqueue(object : Callback<SuccessModel?> {
////            override fun onResponse(call: Call<SuccessModel?>, response: Response<SuccessModel?>) {
////                if (response.isSuccessful && response.body() != null
////                    && response.body()?.success.equals("success") &&
////                    response.body()?.message.equals("chatting Suceessfully")
////                ) {
////                    getChattingList()
////                } else if (response.body()?.success.equals("failure")) {
////                    Toast.makeText(context, "Something Went Wrong", Toast.LENGTH_LONG).show()
////                }
////            }
////
////            override fun onFailure(call: Call<SuccessModel?>, t: Throwable) {
////                t.printStackTrace()
////            }
////        })
////    }
//
//    private fun getChattingList() {
////        binding.progressBar.progressBar.visibility = View.VISIBLE
//        var user_id = mySharedPreferences.userObjectModel?.user_id
//        val retrofit: Retrofit? = RetrofitClient.getInstance(false)
//        val request: WebServices? = retrofit?.create(WebServices::class.java)
//        val call: Call<SuccessModel>? = request?.chatList(user_id)
//        call?.enqueue(object : Callback<SuccessModel?> {
//            override fun onResponse(call: Call<SuccessModel?>, response: Response<SuccessModel?>) {
//                if (response.isSuccessful && response.body() != null
//                    && response.body()?.success.equals("success") &&
//                    response.body()?.message.equals("Chat List")
//                ) {
//                    binding.progressBar.progressBar.visibility = View.GONE
//
//                    chatList.clear()
//                    response.body()?.let {
//                        chatList.addAll(it.Chats)
//                    }
//
//                    if (chatList.size > 0) {
//                        binding.cardFirst.visibility = View.GONE
//                        binding.cardSec.visibility = View.GONE
//                    }
//                    binding.recyclerChat.adapter?.notifyDataSetChanged()
////                    binding.recyclerChat.smoothScrollToPosition(chatList.size-1)
//                    println("chatList.size = ${chatList.size}")
//                    println("binding.recyclerChat.isVisible = ${binding.recyclerChat.isVisible}")
//                    binding.recyclerChat.scrollToPosition(chatList.size)
//                    val requestAdapter = MyChatAdapter(chatList, communityType)
//                    requestAdapter.setOnClickListener(object : MyChatAdapter.OnClickListener {
//                        override fun onClickImage(chatModel: ChatModel) {
//                            println("aaaaaaaaaaa")
////                            chatModel.media = imageUri.toString()
//                            val intent = Intent(fragmentActivity, ViewImageActivity::class.java)
//                            intent.putExtra("imageLink", chatModel.media)
//                            println("imageLink==========" + chatModel.media)
//                            startActivity(intent)
//                        }
//
//                        override fun onClickReply(
//                            chatModel: ChatModel,
//                            item_view_long: LinearLayout
//                        ) {
//
//                        }
//                    })
////                    requestAdapter.setOnMessageLongClick(object :MyChatAdapter.MessageLongClickListener{
////                        override fun onMessageLongClick(chatModel: String?) {
////                            println("Longggggggggggggggg")
////                            Toast.makeText(context,"Longggggggggggggggg",Toast.LENGTH_SHORT).show()
////                        }
////                    })
////                    binding.recyclerChat.setHasFixedSize(true)
////                    linearLayoutManager.stackFromEnd = true
//                } else if (response.body()?.success.equals("failure") &&
//                    response.body()?.message.equals("Data not available")
//                ) {
//                    binding.progressBar.progressBar.visibility = View.GONE
//                    chatList.clear()
//                }
//            }
//
//            override fun onFailure(call: Call<SuccessModel?>, t: Throwable) {
//                t.printStackTrace()
//            }
//        })
//    }
//
//    private fun updateDataInAdapter() {
////        var nameOpen =communityType[0].community_name
////        var nameClose =communityType[1].community_name
////        println("nameOpen=========> $nameOpen")
////        println("nameClose=========> $nameClose")
//        if (binding.open.isChecked) {
//            binding.scrollOpen.visibility = View.VISIBLE
//            binding.scrollClose.visibility = View.GONE
//            binding.recyclerAdminChat.visibility=View.VISIBLE
//            getAdminCommunityMessages()
//            binding.layoutChatChatbox.visibility = View.VISIBLE
//            binding.userMsg12.visibility = View.GONE
//            binding.layoutChatChatboxs.visibility = View.GONE
//            binding.openLayout.visibility = View.GONE
////            if (communityAdmin.isEmpty()) {
//////                binding.userMsg12.visibility = View.VISIBLE
//////                binding.userMsg12.text = "Is Empty"
//////                binding.openLayout.visibility = View.GONE
////            } else {
//////                getAdminCommunityMessages()
////
//////                showDialog()
//////                if (!hasBoughtCourse) {
//////                    showDialog()
//////                    binding.open.isChecked = true
//////                }
//////                binding.layoutChatChatbox.visibility = View.VISIBLE
////            }
//        } else if (binding.close.isChecked) {
//            binding.scrollOpen.visibility = View.GONE
//            binding.scrollClose.visibility = View.VISIBLE
//            if (!hasBoughtCourse) {
////                showDialog()
////                binding.open.isChecked = true
//            }else{
////                hasBoughtCourse = true
//                binding.recyclerAdminChat.visibility=View.GONE
//                binding.userMsg12.visibility = View.GONE
////                binding.progressBar.progressBar.visibility = View.VISIBLE
//                binding.openLayout.visibility = View.VISIBLE
////                binding.recyclerChat.adapter?.notifyDataSetChanged()
////                linearLayoutManager.stackFromEnd = true
////                binding.recyclerChat.adapter =chatAdapter
//                binding.layoutChatChatbox.visibility = View.GONE
////                binding.openLayout.visibility = View.VISIBLE
//                binding.layoutChatChatboxs.visibility = View.VISIBLE
////                binding.close.isEnabled = true
//            }
////            if (openCommunity.isEmpty()) {
//////                binding.userMsg12.visibility = View.VISIBLE
//////                binding.userMsg12.text = "Is Empty"
//////                binding.openLayout.visibility = View.GONE
////
////            } else {
////                binding.userMsg12.visibility = View.GONE
////                binding.progressBar.visibility = View.VISIBLE
////                binding.openLayout.visibility = View.VISIBLE
////                binding.recyclerChat.adapter?.notifyDataSetChanged()
////                linearLayoutManager.stackFromEnd = true
//////                binding.recyclerChat.adapter =chatAdapter
////                binding.layoutChatChatbox.visibility = View.GONE
//////                binding.openLayout.visibility = View.VISIBLE
////                binding.layoutChatChatboxs.visibility = View.VISIBLE
////
////            }
//        }
//    }
//
//    private fun showDialog() {
//        println("DIALOG===========")
////        binding.layoutChatChatbox.visibility = View.GONE
////        binding.userMsg12.visibility = View.GONE
////        binding.layoutChatChatboxs.visibility = View.GONE
////        binding.openLayout.visibility = View.GONE
////                binding.recyclerChat.adapter?.notifyDataSetChanged()
//        val builder = AlertDialog.Builder(context, R.style.CustomAlertDialog)
//            .create()
//        val view = layoutInflater.inflate(R.layout.popup_buy_services, null)
//        val buyNowButton = view.findViewById<CardView>(R.id.buy_now)
//        val serviceButton = view.findViewById<CardView>(R.id.service)
//        val cancel = view.findViewById<ImageView>(R.id.cancel_dialog)
//        builder.setView(view)
//        buyNowButton.setOnClickListener {
//            hasBoughtCourse = true
//            binding.userMsg12.visibility = View.GONE
////                binding.progressBar.visibility = View.VISIBLE
//            binding.openLayout.visibility = View.VISIBLE
//            binding.recyclerChat.adapter?.notifyDataSetChanged()
//            linearLayoutManager.stackFromEnd = true
//            binding.layoutChatChatbox.visibility = View.GONE
//            binding.layoutChatChatboxs.visibility = View.VISIBLE
//            binding.recyclerAdminChat.visibility=View.GONE
//            binding.close.isEnabled = true
//            builder.dismiss()
//        }
//        serviceButton.setOnClickListener {
//            hasBoughtCourse = true
////            Toast.makeText(
////                context,
////                "Coming Soon",
////                Toast.LENGTH_SHORT
////            ).show()
//            builder.dismiss()
//        }
//        cancel.setOnClickListener(View.OnClickListener { builder.dismiss() })
//        builder.setCanceledOnTouchOutside(false)
//        builder.show()
//    }
//
//    private fun setCommunity() {
//        var user_id = mySharedPreferences.userObjectModel?.user_id
//        val retrofit: Retrofit? = RetrofitClient.getInstance(false)
//        val request: WebServices? = retrofit?.create(WebServices::class.java)
//        val call: Call<SuccessModel>? = request?.communities(user_id)
//        call?.enqueue(object : Callback<SuccessModel?> {
//            override fun onResponse(call: Call<SuccessModel?>, response: Response<SuccessModel?>) {
//                if (response.isSuccessful && response.body() != null
//                    && response.body()?.success.equals("success") &&
//                    response.body()?.message.equals("Communities Details")
//
//                ) {
//                    communityType = response.body()!!.Communities
//                    c2 = response.body()!!.Communities[1].community_id.toString()
//                    val c3 = response.body()!!.Communities[0].community_id.toString()
//                    println("c1========================> = ${c1}")
//                    println("c2========================> = ${c2}")
//                    println("c3========================> = ${c3}")
//                    //c2========================> = 2
//                    //c3========================> = 1
//                    if (isFirstCall) {
//                        openCommunity.clear()
////                        closeCommunity.clear()
//                        communityAdmin.clear()
//                    }
//                    for (community in communityType) {
//                        if (community.community_id.equals(c3)) {
//                            openCommunity.add(community)
//                        } else if (community.community_id.equals(c3)) {
//                            communityAdmin.add(community)
//                        }
////                        else if(community.community_id.equals(c2)){
////                            closeCommunity.add(community)
////                        }
//                    }
//                }
//                updateDataInAdapter()
//            }
//
//            override fun onFailure(call: Call<SuccessModel?>, t: Throwable) {
//                t.printStackTrace()
//            }
//        })
//    }
//
//    private fun getAdminCommunityMessages() {
////        binding.progressBar.progressBar.visibility = View.VISIBLE
//        var user_id = mySharedPreferences.userObjectModel?.user_id
//        val retrofit: Retrofit? = RetrofitClient.getInstance(false)
//        val request: WebServices? = retrofit?.create(WebServices::class.java)
//        val call: Call<SuccessModel>? = request?.getCommunityMessages(user_id)
//        call?.enqueue(object : Callback<SuccessModel?> {
//            override fun onResponse(call: Call<SuccessModel?>, response: Response<SuccessModel?>) {
//                if (response.isSuccessful && response.body() != null
//                    && response.body()?.success.equals("success") &&
//                    response.body()?.message.equals("Community Messages")
//                ) {
//                    binding.progressBar.progressBar.visibility = View.GONE
////                    communityAdmin= response.body()!!.CommunityMessage
//                    println("CommunityAdmin++++++++++${communityAdmin}")
//                    communityAdmin.clear()
//                    response.body()?.let {
//                        communityAdmin.addAll(it.CommunityMessage)
//                    }
//
////                    if (communityAdmin.size > 0) {
////                        binding.cardFirst.visibility = View.GONE
////                        binding.cardSec.visibility = View.GONE
////                    }
////                    binding.recyclerAdminChat.adapter?.notifyDataSetChanged()
////                    setAdminRecycler()
//
//                    println("communityAdmin.size ============= ${communityAdmin.size}")
//
//                    linearLayoutManagerAdmin =
//                        LinearLayoutManager(fragmentActivity, LinearLayoutManager.VERTICAL, false)
//                    binding.recyclerAdminChat.layoutManager=linearLayoutManagerAdmin
//                    adminChatAdapter= AdminChatAdapter(communityAdmin)
//                    binding.recyclerAdminChat.adapter=adminChatAdapter
//
//                    binding.recyclerAdminChat.scrollToPosition(communityAdmin.size)
//                    adminChatAdapter.setOnClickListener(object : AdminChatAdapter.OnClickListener {
//                        override fun onClickImage(communityModel: CommunitiesModel) {
//                            println("aaaaaaaaaaa")
////                            chatModel.media = imageUri.toString()
//                            val intent = Intent(fragmentActivity, ViewImageActivity::class.java)
//                            intent.putExtra("imageLink", communityModel.image)
//                            println("imageLink==========" + communityModel.image)
//                            startActivity(intent)
//                        }
//                    })
////                    adminChatAdapter.setonClickListenerV(object :AdminChatAdapter.OnClickListenerV{
////                        override fun onClickVideo(communityModel: CommunitiesModel) {
////                            val intent = Intent(fragmentActivity, ViewVideoActivity::class.java)
////                            intent.putExtra("videoLink", communityModel.video)
////                            println("videoLink==========" + communityModel.video)
////                            startActivity(intent)
////                        }
////                    })
//                } else if (response.body()?.success.equals("failure") &&
//                    response.body()?.message.equals("Data not available")
//                ) {
//                    binding.progressBar.progressBar.visibility = View.GONE
//                }
//            }
//
//            override fun onFailure(call: Call<SuccessModel?>, t: Throwable) {
//                binding.progressBar.progressBar.visibility = View.GONE
//                t.printStackTrace()
//            }
//        })
//    }
//
//    private fun setProfile() {
//        binding.appBar.profile.setOnClickListener(View.OnClickListener {
//            startActivity(
//                Intent(
//                    context,
//                    ProfileActivity::class.java
//                )
//            )
//        })
//    }
//
//    private fun requestCameraPermission() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            println("PERMISSION111111111111111=======================")
//            requestPermissions(cameraPermission, CAMERA_REQUEST)
//
//        } else {
//            Toast.makeText(context, "Permission Issue", Toast.LENGTH_SHORT).show()
//        }
//    }
//
//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray
//    ) {
//        println("aaaaaaaaaaaaaaaaa")
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//
//        if (grantResults.size > 0 && requestCode == CAMERA_REQUEST) {
////            .showCameraTile(false)
//            TedImagePicker.with(requireContext())
//                .start { uri ->
//                    CropImage.activity(uri)
//                        .start(fragmentActivity, this);
//
//                }
//        }
//    }
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
//
//            val result = CropImage.getActivityResult(data)
//            imageUri = result.uri
////            var image = FileUtils.getPath(requireContext(), imageUri)
////            imageFile = File(image)
////            imagesListFile?.add(imageFile!!)
////            saveImages()
//            val intent = Intent(fragmentActivity, EditImageActivity::class.java)
//            intent.putExtra("cropImageUri", imageUri.toString())
//            startActivity(intent)
//
//        } else if (resultCode === CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
//            val result = CropImage.getActivityResult(data)
//            val error = result.error
//        }
//    }
//}
