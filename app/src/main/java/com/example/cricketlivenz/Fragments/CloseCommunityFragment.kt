//package com.example.cricketlivenz.Fragments
//
//import android.Manifest
//import android.app.Dialog
//import android.content.Context
//import android.content.Intent
//import android.content.pm.PackageManager
//import android.net.Uri
//import android.os.Build
//import android.os.Bundle
//import android.text.Editable
//import android.text.TextWatcher
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
//import androidx.core.app.ActivityCompat
//import androidx.core.content.ContextCompat
//import androidx.core.view.get
//import androidx.fragment.app.Fragment
//import androidx.fragment.app.FragmentActivity
//import androidx.recyclerview.widget.ItemTouchHelper
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//
//import java.io.File
//import java.sql.DriverManager
//import java.util.Timer
//import java.util.TimerTask
//
//
//class CloseCommunityFragment : Fragment() {
//    private lateinit var binding: FragmentCloseCommunityBinding
//    private lateinit var fragmentActivity: FragmentActivity
//    private lateinit var linearLayoutManager: LinearLayoutManager
//    private lateinit var mySharedPreferences: MySharedPreferences
//    private var chatList = ArrayList<ChatModel>()
//    private var communityType = ArrayList<CommunitiesModel>()
//    private val CAMERA_REQUEST = 100
//    private lateinit var cameraPermission: Array<String>
//    private var c1 = ""
//    private var media = ""
//    private var mediaTypeImage = ""
//    private var mediaTypeVideo = ""
//    private var mediaType = ""
//    private var limit = 50
//    private var offset = 0
//    var reloadTimer: Timer? = null
//    var reloadChatListCount = 0
//    var lastScrolledLocation = 0
//    private lateinit var requestAdapter: MyChatAdapter
//
//    //new
//    var reply_status = ""
//    var reply_chat_id = ""
//    var myTimerNote: Timer? = null
//    var replyColorStatus = false
//    var replyColorIndex = 0
//    var imagesListFile: java.util.ArrayList<File>? = arrayListOf<File>()
//    lateinit var singleVideoLauncher: ActivityResultLauncher<PickVisualMediaRequest>
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        binding = FragmentCloseCommunityBinding.inflate(inflater, container, false)
//        mySharedPreferences = MySharedPreferences.getInstance(context)!!
//        fragmentActivity = context as FragmentActivity
//        linearLayoutManager =
//            LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
//        binding.recyclerChat.layoutManager = linearLayoutManager
//        linearLayoutManager.stackFromEnd = true
//        requestAdapter = MyChatAdapter(chatList, communityType)
//        binding.recyclerChat.adapter = requestAdapter
//        requestAdapter.setOnClickListener(object : MyChatAdapter.OnClickListener {
//            override fun onClickImage(chatModel: ChatModel) {
//                val intent = Intent(fragmentActivity, ViewImageActivity::class.java)
//                intent.putExtra("imageLink", chatModel.media)
//                startActivity(intent)
//            }
//
//            override fun onClickReply(chatModel: ChatModel, item_view_long: LinearLayout) {
////                val index = chatList.indexOfFirst { it.chat_id == chatModel.reply_chat_id }
////                println("index = ${index}")
////                if (index != -1) {
////                    replyColorStatus = true
////                    replyColorIndex = index
//////                    requestAdapter.notifyDataSetChanged()
//////                    val offset = linearLayoutManager.findViewByPosition(index)?.top ?: 0
////                    println("11111111111111111111111111111111")
//////                    (binding.recyclerChat.layoutManager as LinearLayoutManager).scrollToPositionWithOffset(index, offset)
//////                    binding.recyclerChat.smoothScrollBy(0, item_view_long.height)
////                    binding.recyclerChat.smoothScrollToPosition(index)
////                }
//                var index = 0
//                for (obj in chatList) {
//                    if (obj.chat_id == chatModel.reply_chat_id) {
//                        break
//                    }
//                    index++
//                }
//                replyColorStatus = true
//                replyColorIndex = index
////                item_view_long.background =resources.getDrawable(R.drawable.white_round_background_qna)
//                binding.recyclerChat.smoothScrollToPosition(index)
//                binding.recyclerChat.itemAnimator=null
//            }
//        })
//        requestAdapter.setonClickListenerV(object : MyChatAdapter.OnClickListenerV {
//            override fun onClickVideo(chatModel: ChatModel) {
//                println("aaaaaaaaaaavvvvvvv")
////                            chatModel.media = imageUri.toString()
//                val intent = Intent(fragmentActivity, ViewVideoActivity::class.java)
//                intent.putExtra("videoLink", chatModel.media)
//                println("videoLink==========" + chatModel.media)
//                startActivity(intent)
//            }
//        })
//        requestAdapter.setOnMessageLongClick(object :
//            MyChatAdapter.MessageLongClickListener {
//            override fun onMessageLongClick(chatModel: ChatModel) {
//                showQuotedMessage(chatModel)
//            }
//        })
//
//        binding.recyclerChat.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//
//                println("222222222222222222222222")
//
//                if (replyColorStatus) {
////                    val view = binding.recyclerChat[dx]
////                    view.background = resources.getDrawable(R.drawable.white_round_background_qna)
//                    replyColorStatus = false
//                    replyColorIndex = 0
//                }
//
////                var lastLoc = linearLayoutManager.findFirstCompletelyVisibleItemPosition()
////                println("lastLoc ======= ${lastLoc}")
////                if (lastLoc + 1 == 1) {
////                    offset += limit
////                    getChattingList()
////                }
//            }
//        })
////        requestAdapter.setOnMsgClickListener(object : MyChatAdapter.Unit {
//////            override fun onMsgClickListener(chatModel: ChatModel) {
//////                println("Is working11111 =====>")
//////                val personMessage = findPersonMessage(chatModel)
//////                personMessage.let { scrollToMessage(it) }
//////            }
////
////            override fun invoke(message: ChatModel) {
////                println("Is working22222222 =====>")
////
////                val personMessage = findPersonMessage(message)
////                println("Is personMessage =====>$personMessage")
////
////                personMessage.let { scrollToMessage(it) }
////            }
////
////        })
//        setCommunity()
//        initData()
//        startNotificationTimer()
//        binding.imgAttachImage.setOnClickListener {
////            requestCameraPermission()
//            selectionOption()
//        }
////        reloadChatList()
//        return binding.root
//    }
//
//    private fun scrollToMessage(it: ChatModel?) {
//        val position = requestAdapter.mList?.indexOf(it)
//        println("Scrolling Position======> $position")
//        if (position != -1) {
//            if (position != null) {
//                binding.recyclerChat.scrollToPosition(position)
//            }
//        }
//    }
//
//    private fun findPersonMessage(clickedMessage: ChatModel): ChatModel? {
//        return requestAdapter.mList?.firstOrNull {
//            it.chat_id != clickedMessage.chat_id
//        }
//        println("=================clickedMessage=====>$clickedMessage")
//    }
//
//    private fun initData() {
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
//        binding.buttonGchatSend.setOnClickListener(View.OnClickListener {
//            if (binding.editGchatMessages.text.toString() == "") {
//                hideReplyLayout()
//                Toast.makeText(context, "Please Type Something", Toast.LENGTH_SHORT)
//                    .show()
//            } else {
//                hideReplyLayout()
//                var message = binding.editGchatMessages.text.toString()
//                binding.editGchatMessages.setText("")
//                binding.editGchatMessages.minLines = 1
//
//                var chatModel = ChatModel(
//                    "", "", "", mySharedPreferences.userObjectModel?.user_id, message,
//                    "", "", "", reply_status, reply_chat_id, ""
//                )
//                chatList.add(chatModel)
//                requestAdapter.notifyDataSetChanged()
//                binding.recyclerChat.itemAnimator = null
////                var requestAdapter = MyChatAdapter(chatList, communityType)
////                binding.recyclerChat.adapter = requestAdapter
//                binding.recyclerChat.smoothScrollToPosition(chatList.size - 1)
//                linearLayoutManager.stackFromEnd = true
//
//                sendMessageToFriend(message)
//            }
//        })
//
////        binding.editGchatMessages.setOnTouchListener(object : View.OnTouchListener {
////            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
////                if (event?.action == MotionEvent.ACTION_UP) {
////                    if (event.getRawX() >= binding.editGchatMessages.right - binding.editGchatMessages.compoundDrawables[2].bounds.width()) {
////                        requestCameraPermission()
////                        return true
////                    }
////                }
////                return false
////            }
////        })
//
//        binding.editGchatMessages.addTextChangedListener(object : TextWatcher {
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//            }
//
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//            }
//
//            override fun afterTextChanged(s: Editable?) {
//                if (binding.editGchatMessages.lineCount == binding.editGchatMessages.maxLines) {
//                    binding.editGchatMessages.maxLines = (binding.editGchatMessages.lineCount + 1)
//                }
//                if (s.toString().length > 100) {
//                    binding.editGchatMessages.height = 500
//                }
//            }
//        })
//
//
//        val messageSwipeController =
//            SwipeToReplyCallBack(requireContext(), object : SwipeControllerActions {
//                override fun showReplyUI(position: Int) {
//                    println("position============= $position")
////                    quotedMessagePos = position
//                    showQuotedMessage(chatList[position])
//                }
//            })
//
//        val itemTouchHelper = ItemTouchHelper(messageSwipeController)
//        itemTouchHelper.attachToRecyclerView(binding.recyclerChat)
//
//
//        binding.cancelButton.setOnClickListener {
//            hideReplyLayout()
//            reply_status = ""
//            reply_chat_id = ""
//        }
//
//        singleVideoLauncher =
//            registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
//                if (uri != null) {
//                    imagesListFile?.clear()
//                    var image = FileUtils.getPath(requireContext(), uri)
//                    var imageFile = File(image)
//                    imagesListFile?.add(imageFile)
//                    sureUploadVideo(uri)
//                } else {
//
//                }
//
//            }
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
//    private fun sureUploadVideo(uri: Uri) {
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
//            saveChatVideo()
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
//    private fun saveChatVideo() {
//        binding.progressBar.progressBar.visibility = View.VISIBLE
//        val multipartUploadRequest = MultipartUploadRequest(
//            requireContext(), ServerConnect.BASE_URL + "Chats"
//        )
//        multipartUploadRequest.addHeader(
//            "Authorization",
//            Credentials.basic("tradr", "tradrc1b9fe16e7d1e9c84ccb218fa7bf69")
//        )
//            .addHeader("X-API-KEY", "lxZhw9eI0bm3XWjiwcjiZjT7DpkdpkZjajAvT33qMAY=")
//            .addParameter("community_id", c1)
//            .addParameter("message", binding.editGchatMessages.text.toString())
//            .addParameter("user_id", mySharedPreferences.userObjectModel?.user_id.toString())
//            .addParameter("media_type", "Video")
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
//                    getChattingList()
//                    setCommunity()
//                }
//            })
//    }
//
//    private fun sendMessageToFriend(message: String) {
//        var user_id = mySharedPreferences.userObjectModel?.user_id
//        val retrofit: Retrofit? = RetrofitClient.getInstance(false)
//        val request: WebServices? = retrofit?.create(WebServices::class.java)
//        val call: Call<SuccessModel>? =
//            request?.sendChat(c1, message, user_id, media, mediaType, reply_status, reply_chat_id)
//
////        val call: Call<SuccessModel>? = request?.sendChat(c1, message, user_id, media)
//        call?.enqueue(object : Callback<SuccessModel?> {
//            override fun onResponse(call: Call<SuccessModel?>, response: Response<SuccessModel?>) {
//                if (response.isSuccessful && response.body() != null
//                    && response.body()?.success.equals("success") &&
//                    response.body()?.message.equals("chatting Suceessfully")
//                ) {
//                    getChattingList()
//                } else if (response.body()?.success.equals("failure")) {
//                    Toast.makeText(context, "Something Went Wrong", Toast.LENGTH_LONG).show()
//                }
//                reply_status = ""
//                reply_chat_id = ""
//                mediaType = " "
//            }
//
//            override fun onFailure(call: Call<SuccessModel?>, t: Throwable) {
//                t.printStackTrace()
//                reply_status = ""
//                reply_chat_id = ""
//            }
//        })
//    }
//
//    private fun getChattingList() {
//        binding.progressBar.progressBar.visibility = View.VISIBLE
//        println("offset =============== ${offset}")
//        var user_id = mySharedPreferences.userObjectModel?.user_id
//        val retrofit: Retrofit? = RetrofitClient.getInstance(false)
//        val request: WebServices? = retrofit?.create(WebServices::class.java)
//        val call: Call<SuccessModel>? = request?.chatList(user_id)
////        val call: Call<SuccessModel>? =
////            request?.chatList2(user_id, limit.toString(), offset.toString())
//        call?.enqueue(object : Callback<SuccessModel?> {
//            override fun onResponse(call: Call<SuccessModel?>, response: Response<SuccessModel?>) {
//                if (response.isSuccessful && response.body() != null
//                    && response.body()?.success.equals("success") &&
//                    response.body()?.message.equals("Chat List")
//                ) {
//                    binding.progressBar.progressBar.visibility = View.GONE
//                    chatList.clear()
//                    response.body()?.let {
//                        chatList.addAll(it.Chats)
////                        chatList.addAll(0, it.Chats)
//                    }
//
////                    if (lastScrolledLocation == 0) {
////                        linearLayoutManager.scrollToPosition(chatList.size)
////                    } else {
////                        linearLayoutManager.scrollToPosition((chatList.size - offset) + 2)
////                    }
////                    lastScrolledLocation++
//
//                    requestAdapter.notifyDataSetChanged()
////                    linearLayoutManager.stackFromEnd = true
//                    linearLayoutManager.scrollToPosition(chatList.size - 1)
//
//                    if (chatList.isEmpty()) {
//                        binding.recyclerChat.visibility = View.GONE
//                        binding.rltNoData.visibility = View.VISIBLE
//                    }
//                } else if (response.body()?.success.equals("failure") &&
//                    response.body()?.message.equals("Data not available")
//                ) {
//                    binding.progressBar.progressBar.visibility = View.GONE
//                    chatList.clear()
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
//                ) {
//                    communityType = response.body()!!.Communities
//                    c1 = response.body()!!.Communities[1].community_id.toString()
////                    c1 = response.body()!!.Communities[0].community_id.toString()
//                    getChattingList()
//                }
//            }
//
//            override fun onFailure(call: Call<SuccessModel?>, t: Throwable) {
//                t.printStackTrace()
//            }
//        })
//    }
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
//                        if (osNotification?.additionalData == null || osNotification.additionalData.length() == 0) {
//                        } else {
//                            var type = osNotification.additionalData?.getJSONArray("type")
//                            println("type =-------- ${type}")
////                            println("type!!.get(0) = ${type!!.get(0)}")
////                            if (type!!.get(0) == "Message Notification") {
//                            offset = 0
////                            chatList.clear()
//                            getChattingList()
//                            NotificationServiceExtension.osNotification = null
////                            }
//                        }
//                    }
//                } catch (e: Exception) {
//                    e.printStackTrace()
//                }
//            }
//        }, 0, 3000)
//    }
//
//    //    private fun showQuotedMessage(message: String, sender: String, image: String) {
//    private fun showQuotedMessage(chatModel: ChatModel) {
//        reply_status = "Yes"
//        mediaType = "Image"
//        reply_chat_id = chatModel.chat_id.toString()
//        binding.editGchatMessages.requestFocus()
//        binding.txtQuotedMsg1.text = chatModel.message
//        binding.sender.text = chatModel.sender_name
//        //        if (chatModel.media_type == "Image" && chatModel.media!!.isNotEmpty()) {
//        if (chatModel.media!!.isNotEmpty() || chatModel.media_type == "Image") {
//            Glide.with(requireContext())
//                .load(chatModel.media!!)
//                .apply(
//                    RequestOptions()
//                        .diskCacheStrategy(DiskCacheStrategy.NONE)
//
//                )
//                .into(binding.image)
//        } else {
//            binding.image.visibility = View.GONE
//        }
//        if (chatModel.media_type == "Video" && chatModel.media!!.isNotEmpty()) {
////            binding.image.visibility = View.GONE
//            binding.replyVideoAttachedLayout.visibility = View.VISIBLE
//            binding.replyVideoThumbnail.visibility = View.VISIBLE
//            binding.replyVideoOverlay.visibility = View.VISIBLE
//            Glide.with(binding.replyVideoThumbnail.context).load(chatModel.media)
//                .placeholder(R.drawable.dummy_v_t)
//                .diskCacheStrategy(
//                    DiskCacheStrategy.DATA
//                ).centerCrop().into(binding.replyVideoThumbnail)
//        } else {
//            binding.replyVideoAttachedLayout.visibility = View.GONE
//            binding.replyVideoThumbnail.visibility = View.GONE
//            binding.replyVideoOverlay.visibility = View.GONE
//        }
//        binding.replyLayout.visibility = View.VISIBLE
//
//    }
//
//    private fun hideReplyLayout() {
//        binding.replyLayout.visibility = View.GONE
//
//    }
//
//
//    private fun requestCameraPermission() {
//        println("checkPermission() = ${checkPermission()}")
//        if (checkPermission()) {
//            TedImagePicker.with(requireContext())
//                .start { uri ->
//                    CropImage.activity(uri).start(fragmentActivity, this)
//                }
//        } else {
//            println("aaaaaaaaaaaaaaaaa")
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                println("bbbbbbbbbbbbbbbbb")
//                requestPermissions(cameraPermission, CAMERA_REQUEST)
//            }
//        }
////        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
////            requestPermissions(cameraPermission, CAMERA_REQUEST)
////        } else {
////            Toast.makeText(context, "Permission Issue", Toast.LENGTH_SHORT).show()
////        }
//    }
//
//    private fun checkPermission(): Boolean {
//        return ContextCompat.checkSelfPermission(
//            fragmentActivity,
//            Manifest.permission.CAMERA
//        ) == PackageManager.PERMISSION_GRANTED
//    }
//
//    private fun requestPermission() {
//        ActivityCompat.requestPermissions(
//            fragmentActivity, arrayOf<String>(Manifest.permission.CAMERA),
//            1001
//        )
//    }
//
//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray
//    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        println("ccccccccccccccc")
//        if (grantResults.size > 0 && requestCode == CAMERA_REQUEST) {
//            TedImagePicker.with(requireContext())
//                .start { uri ->
//                    CropImage.activity(uri).start(fragmentActivity, this)
//                }
//        } else {
//            Toast.makeText(
//                fragmentActivity,
//                "Please Allow Camera Permission from Settings",
//                Toast.LENGTH_SHORT
//            ).show()
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
//                intent.putExtra("community", c1)
//                startActivity(intent)
//
//            } else {
//            }
//        }
//    }
//
//    private fun reloadChatList() {
//        reloadChatListCount++
//        reloadTimer = Timer()
//        reloadTimer!!.scheduleAtFixedRate(object : TimerTask() {
//            override fun run() {
//
//                try {
//                    println("timerrrrrrrrrrrrrrrrrrrrrrrrrr")
//                    getChattingList()
//                    if (reloadChatListCount > 1) {
//                        reloadTimer!!.cancel()
//                    }
//                } catch (e: Exception) {
//                    e.printStackTrace()
//                }
//            }
//        }, 0, 10000)
//    }
//}