package com.example.cricketlivenz
//
//featuresNew

import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationManagerCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.cricketlivenz.Fragments.CommunityListFragment
import com.example.cricketlivenz.Fragments.CoursesFragment
import com.example.cricketlivenz.Fragments.HomeFragment
import com.example.cricketlivenz.Fragments.ProfileFragment
import com.example.cricketlivenz.Fragments.StrategyFragment
import com.example.cricketlivenz.Fragments.TradrFragment
import com.example.cricketlivenz.databinding.ActivityMainBinding
import com.google.android.play.core.appupdate.AppUpdateInfo
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.InstallState
import com.google.android.play.core.install.InstallStateUpdatedListener
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.InstallStatus
import com.google.android.play.core.install.model.UpdateAvailability

import java.util.Timer
import java.util.TimerTask


class MainActivity : AppCompatActivity(),
    InstallStateUpdatedListener {
    private lateinit var binding: ActivityMainBinding
  //  private lateinit var mySharedPreferences: MySharedPreferences
    var myTimerNote: Timer? = null
    private var isNotificationClicked = false
    private var notificationReadCount = "0"
  //  private lateinit var appUpdateManager: AppUpdateManager
    //private var currentType = AppUpdateType.IMMEDIATE
    private val MY_REQUEST_CODE = 2023
    var currentFragment = ""
    var redirectFrom = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      //  mySharedPreferences = MySharedPreferences.getInstance(this)!!
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initData()
        println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa")
      //  val userId = mySharedPreferences.userObjectModel?.user_id
//        if (userId != null) {
//          //  getUserInfo(userId)
//        }
        checkNotificationPermission()
       // setClickOnAppBarItems()
        val extras = intent?.extras
        if (extras == null) {
        } else {
            var editKey = intent?.extras!!.getString("editKey")
            if (editKey == "editKey") {
                setCommunityFragmentWithBundle()
//                binding.bottomNavigationView.setSelectedItemId(R.id.community)
            }
        }

        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                @RequiresApi(Build.VERSION_CODES.O)
                override fun handleOnBackPressed() {
                    fragmentManager.popBackStack()///18004190157

                    println("getSupportFragmentManager().findFragmentByTag = ${supportFragmentManager.backStackEntryCount}")
                    println("getSupportFragmentManager().findFragmentByTag = ${supportFragmentManager.fragments}")
                    println("getSupportFragmentManager().findFragmentByTag = ${supportFragmentManager.findFragmentByTag("home")}")
                    val findFragmentByTag = supportFragmentManager.findFragmentByTag("home")
                    println("findFragmentByTag!!.id ===== ${findFragmentByTag!!.tag}")

                    val get = supportFragmentManager.fragments.get(0)
                    println("get ====== ${get}")
                    println("get tag ====== ${get.tag}")

                    if (get!!.tag == "home" || currentFragment == "home"  ) {
                        //exitBlock()
                    }else{
                        binding.bottomNavigationView.selectedItemId = R.id.home
                        binding.appBar.logo.visibility = View.VISIBLE
                        loadFragment(HomeFragment(), "home")

                        binding.navigateHome.visibility = View.VISIBLE
                        val menu: Menu = binding.bottomNavigationView.menu
                        binding.bottomAppBar.fabCradleMargin = resources.getDimension(R.dimen._8ssp)
                        binding.bottomAppBar.fabCradleRoundedCornerRadius = resources.getDimension(R.dimen._0ssp)
                        menu.findItem(R.id.home).icon = null
                        menu.findItem(R.id.home).title = ""
                    }
                }
            }
        this.onBackPressedDispatcher.addCallback(this, callback)
    }


//    private fun exitBlock() {
//        val dialog = Dialog(this@MainActivity, R.style.MaterialDialogSheet)
//        dialog.setContentView(R.layout.dialog_logout_layout)
//        val view = layoutInflater.inflate(R.layout.dialog_logout_layout, null)
//        val button = view.findViewById<TextView>(R.id.btnYes)
//        val txtGearType = view.findViewById<TextView>(R.id.txtGearType)
//        txtGearType.text = "Would you like to Exit?"
//        dialog.setContentView(view)
//        button.setOnClickListener {
//            dialog.dismiss()
//            finishAffinity()
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

    private fun setCommunityFragmentWithBundle() {
        val bundle = Bundle()
        val extras = intent.extras
        println("extras =??? ${extras}")
        val FinalImageUri = Uri.parse(extras!!.getString("FinalImageUri"))
//        val FinalImageUri = Uri.parse(intent?.extras!!.getString("FinalImageUri"))
        val FinalText = intent?.extras!!.getString("FinalText")
        val community = intent?.extras!!.getString("community")
        bundle.putString("finalImage", FinalImageUri.toString())
        bundle.putString("finalText", FinalText)
        bundle.putString("community", community)
//        val communityFragment = CommunityFragment()
        val communityFragment = CommunityListFragment()
        communityFragment.arguments = bundle
        loadFragment(communityFragment,"community")

        binding.appBar.logo.visibility = View.GONE

        binding.navigateHome.visibility = View.GONE
        binding.bottomAppBar.fabCradleMargin = resources.getDimension(R.dimen._0ssp)
        binding.bottomAppBar.fabCradleRoundedCornerRadius = resources.getDimension(R.dimen._0ssp)
        val menu: Menu = binding.bottomNavigationView.menu
        menu.findItem(R.id.home).setIcon(R.drawable.home)
        menu.findItem(R.id.home).title = "Home"
//        binding.bottomNavigationView.setSelectedItemId(R.id.community)

    }


//    private fun setClickOnAppBarItems() {
//        binding.appBar.notification.setOnClickListener(View.OnClickListener {
//            binding.appBar.readCount.text = "0"
//            startActivity(Intent(this, NotificationActivity::class.java))
//        })
//        binding.appBar.profile.setOnClickListener(View.OnClickListener {
//            startActivity(
//                Intent(
//                    this,
//                    ProfileActivity::class.java
//                )
//            )
//        })
//        binding.appBar.drawerLayout.setOnClickListener(View.OnClickListener {
////            openAndCloseDrawer()
//            Toast.makeText(
//                this,
//                "Coming Soon",
//                Toast.LENGTH_LONG
//            ).show()
//        })
////        binding.navigationMenu.myProfile.setOnClickListener() {
////            startActivity(
////                Intent(
////                    this,
////                    ProfileActivity::class.java
////                )
////            )
////        }
////        manageDrawerProfile()
//    }

    private fun checkNotificationPermission() {
        if (NotificationManagerCompat.from(applicationContext).areNotificationsEnabled()) {
            println("GRANTED================")
        } else {
            println("NOT GRANTED================")
//            requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            val settingsIntent: Intent = Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                .putExtra(Settings.EXTRA_APP_PACKAGE, packageName)
                .putExtra(Settings.EXTRA_CHANNEL_ID, 202)
            startActivity(settingsIntent)
        }
    }


//    private fun getUserInfo(userId: String) {
//        val retrofit: Retrofit? = RetrofitClient.getInstance(false)
//        val request: WebServices? = retrofit?.create(WebServices::class.java)
//        val call: Call<UserInformationModel>? = request?.getProfileDetails(userId)
//        call?.enqueue(object : Callback<UserInformationModel?> {
//            override fun onResponse(
//                call: Call<UserInformationModel?>,
//                response: Response<UserInformationModel?>
//            ) {
//                if (response.isSuccessful && response.body() != null && response.body()?.success.equals(
//                        "Profile Details"
//                    )
//                ) {
//                    var userInformationModel = response.body() as UserInformationModel
//                    var userId = ""
//                    try {
//                        notificationReadCount = userInformationModel.unread_notifications.toString()
//                        if (notificationReadCount == "") {
//                            binding.appBar.readCount.text = "0"
//                        } else {
//                            binding.appBar.readCount.text = notificationReadCount
//                        }
//                        OneSignal.setLogLevel(
//                            OneSignal.LOG_LEVEL.VERBOSE,
//                            OneSignal.LOG_LEVEL.NONE
//                        )
//                        var appId = getString(R.string.onesignal_app_id)
//                        OneSignal.setAppId(appId)
//                        OneSignal.initWithContext(baseContext)
//                        userId = OneSignal.getDeviceState()!!.userId
//
//                        println("userId ======== ${userId}")
//
//                        println("userInformationModel.app_id = ${userInformationModel.app_id}")
//
//                        if (userInformationModel.app_id == "" || userInformationModel.app_id == null ||
//                            userInformationModel.app_id == "null"
//                        ) {
//                            updateAppId(userId)
//                        } else {
//                            if (userInformationModel.app_id.toString() != userId) {
//                                updateAppId(userId)
//                            }
//                        }
//
//
//                    } catch (e: Exception) {
//                        e.printStackTrace()
//                    }
//
//                } else {
//                    Toast.makeText(this@MainActivity, "Failure", Toast.LENGTH_SHORT).show()
//                }
//            }
//
//            override fun onFailure(call: Call<UserInformationModel?>, t: Throwable) {
//                t.printStackTrace()
//            }
//
//        })
//    }

//    private fun updateAppId(user_app_id: String) {
//        var usermodel = mySharedPreferences.userObjectModel
//        val retrofit: Retrofit? = RetrofitClient.getInstance(false)
//        val request: WebServices? = retrofit?.create(WebServices::class.java)
//        val call: Call<UserInformationModel>? =
//            request?.updateAppId(usermodel?.user_id, user_app_id)
//        call?.enqueue(object : Callback<UserInformationModel?> {
//            override fun onResponse(
//                call: Call<UserInformationModel?>,
//                response: Response<UserInformationModel?>
//            ) {
//                if (response.isSuccessful && response.body() != null
//                    && response.body()?.success.equals("Profile Details")
//                ) {
//                    var userModel = response.body() as UserInformationModel
//                    println("userModel ===== ${userModel}")
//                    if (userModel != null) {
//                        userModel.app_id = user_app_id
//                        mySharedPreferences.setUserModel(userModel)
//                    }
//                } else if (response.isSuccessful && response.body() != null
//                    && response.body()?.success.equals("failure")
//                ) {
//                }
//            }
//
//            override fun onFailure(call: Call<UserInformationModel?>, t: Throwable) {
//                t.printStackTrace()
//            }
//        })
//    }

    private fun initData() {

//        checkForAnUpdate()
//        window.statusBarColor = ContextCompat.getColor(this, R.color.main_primary_28223b)
//        if (isNotificationClicked)
//        {
//            loadFragment(HomeFragment())
//        }
        binding.navigateHome.setOnClickListener(View.OnClickListener {
            binding.bottomNavigationView.selectedItemId = R.id.home
            binding.appBar.logo.visibility = View.VISIBLE
            loadFragment(HomeFragment(), "home")

            binding.navigateHome.visibility = View.VISIBLE
            val menu: Menu = binding.bottomNavigationView.menu
            binding.bottomAppBar.fabCradleMargin = resources.getDimension(R.dimen._10ssp)
            binding.bottomAppBar.fabCradleRoundedCornerRadius = resources.getDimension(R.dimen._20ssp)
            menu.findItem(R.id.home).icon = null
            menu.findItem(R.id.home).title = ""
        })
//        loadFragment(HomeFragment())
        selectBottomViewItems()
//        getWindow().setFlags(
//            WindowManager.LayoutParams.FLAG_SECURE,
//            WindowManager.LayoutParams.FLAG_SECURE
//        );
//       if (!isNotificationClicked){
//           val intent = intent.extras
//           var notificationPayload = intent?.getString("notificationPayload").toString()
//           val bundle = Bundle()
//           bundle.putString("notificationPayload", notificationPayload)
//           val fragobj = CommunityFragment()
//           fragobj.arguments = bundle
//           loadFragment(CommunityFragment())
//       }else
//       {
//           loadFragment(HomeFragment())
//       }
        val intent1 = intent.extras
        var messageFromOnesignal = intent1?.getString("messageFromOnesignal").toString()

        println("messageFromOnesignal ======= ${messageFromOnesignal}")
        if (messageFromOnesignal == "yes") {
            val intent = intent.extras
            var notificationPayload = intent?.getString("notificationPayload").toString()
            val bundle = Bundle()
//            bundle.putString("notificationPayload", notificationPayload)
//            println("notificationPayload ========= ${notificationPayload}")
            bundle.putString("notificationPayload", "openCloseFragment")
//            val fragobj = CommunityFragment()
            val fragobj = CommunityListFragment()
            fragobj.arguments = bundle

            loadFragment(fragobj, "community")
            binding.navigateHome.visibility = View.GONE
            binding.bottomAppBar.fabCradleMargin = resources.getDimension(R.dimen._0ssp)
            binding.bottomAppBar.fabCradleRoundedCornerRadius = resources.getDimension(R.dimen._0ssp)
            val menu: Menu = binding.bottomNavigationView.menu
//            binding.bottomAppBar.background=resources.getDrawable(R.drawable.bg_gradient)
//            binding.bottomAppBar.fabCradleMargin = resources.getDimension(R.dimen._8ssp)
//            binding.bottomAppBar.fabCradleRoundedCornerRadius = resources.getDimension(R.dimen._0ssp)
            menu.findItem(R.id.home).setIcon(R.drawable.home)
            menu.findItem(R.id.home).title = "Home"
        } else if (messageFromOnesignal == "admin") {
            val bundle = Bundle()
            bundle.putString("notificationPayload", "adminFragment")
            val fragobj = CommunityListFragment()
            fragobj.arguments = bundle
            loadFragment(fragobj, "community")
            binding.navigateHome.visibility = View.GONE
            binding.bottomAppBar.fabCradleMargin = resources.getDimension(R.dimen._0ssp)
            binding.bottomAppBar.fabCradleRoundedCornerRadius = resources.getDimension(R.dimen._0ssp)
            val menu: Menu = binding.bottomNavigationView.menu
//            binding.bottomAppBar.background = resources.getDrawable(R.drawable.bg_gradient)
            menu.findItem(R.id.home).setIcon(R.drawable.home)
            menu.findItem(R.id.home).title = "Home"
        } else {
            redirectFrom = intent1?.getString("redirectFrom").toString()
            if (redirectFrom == "Profile") {
                setTradrFragment()
            } else {
                loadFragment(HomeFragment(), "home")
            }
        }


//        binding.navigateHome.setOnClickListener(View.OnClickListener {
//            loadFragment(HomeFragment())
//        })
//        startNotificationTimer()

    }

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
//                            println("type =????????? ${type}")
//                            NotificationServiceExtension.osNotification = null
//                        }
//                    }
//                } catch (e: Exception) {
//                    e.printStackTrace()
//                }
//            }
//        }, 0, 3000)
//    }

    private fun selectBottomViewItems() {
        binding.bottomNavigationView.selectedItemId = R.id.home
        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.community -> {
                    setCommunityFragment()
                    currentFragment="community"
                    true
                }

                R.id.strategy -> {
                    setStrategyFragment()
                    currentFragment="strategy"

                    true
                }

                R.id.home -> {
                    setHomeFragment()
                    currentFragment="home"

                    true
                }

                R.id.tradr -> {
                    setTradrFragment()
                    currentFragment="tradr"

                    true
                }

                R.id.courses -> {
                    setCoursesFragment()
                    currentFragment="More"

                    true
                }

                else -> {
                    false
                }
            }
        }

    }

    private fun setCoursesFragment() {
        loadFragment(ProfileFragment(), "More")
        binding.appBar.logo.visibility = View.VISIBLE

        binding.navigateHome.visibility = View.VISIBLE
        val menu: Menu = binding.bottomNavigationView.menu
        binding.bottomAppBar.fabCradleMargin = resources.getDimension(R.dimen._10ssp)
        binding.bottomAppBar.fabCradleRoundedCornerRadius = resources.getDimension(R.dimen._20ssp)
        menu.findItem(R.id.home).icon = null
        menu.findItem(R.id.home).title = ""
    }

    private fun setStrategyFragment() {
        loadFragment(StrategyFragment(), "strategy")
        binding.appBar.logo.visibility = View.VISIBLE

        binding.navigateHome.visibility = View.VISIBLE
        val menu: Menu = binding.bottomNavigationView.menu
        binding.bottomAppBar.fabCradleMargin = resources.getDimension(R.dimen._10ssp)
        binding.bottomAppBar.fabCradleRoundedCornerRadius = resources.getDimension(R.dimen._20ssp)
        menu.findItem(R.id.home).icon = null
        menu.findItem(R.id.home).title = ""
    }

    private fun setTradrFragment() {

        loadFragment(TradrFragment(), "tradr")
        binding.appBar.logo.visibility = View.VISIBLE

        binding.navigateHome.visibility = View.VISIBLE
        val menu: Menu = binding.bottomNavigationView.menu
        binding.bottomAppBar.fabCradleMargin = resources.getDimension(R.dimen._10ssp)
        binding.bottomAppBar.fabCradleRoundedCornerRadius = resources.getDimension(R.dimen._20ssp)
        menu.findItem(R.id.home).icon = null
        menu.findItem(R.id.home).title = ""
    }

    private fun setHomeFragment() {
        binding.appBar.logo.visibility = View.VISIBLE
//        binding.appBar.screenName.visibility = View.GONE
        loadFragment(HomeFragment(), "home")

        binding.navigateHome.visibility = View.VISIBLE
        val menu: Menu =
            binding.bottomNavigationView.menu
        binding.bottomAppBar.fabCradleMargin = resources.getDimension(R.dimen._10ssp)
        binding.bottomAppBar.fabCradleRoundedCornerRadius = resources.getDimension(R.dimen._20ssp)


        menu.findItem(R.id.home).icon = null
        menu.findItem(R.id.home).title = ""

    }

    private fun setCommunityFragment() {
//        loadFragment(CommunityFragment())
        loadFragment(CommunityListFragment(), "community")
        binding.appBar.logo.visibility = View.VISIBLE

        binding.navigateHome.visibility = View.GONE
        binding.bottomAppBar.fabCradleMargin = resources.getDimension(R.dimen._0ssp)
        binding.bottomAppBar.fabCradleRoundedCornerRadius = resources.getDimension(R.dimen._0ssp)
        val menu: Menu = binding.bottomNavigationView.menu
        menu.findItem(R.id.home).setIcon(R.drawable.home)
        binding.bottomAppBar.background = resources.getDrawable(R.drawable.bg_gradient)

        menu.findItem(R.id.home).title = "Home"
    }

    private fun loadFragment(fragment: Fragment, tag: String) {
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container, fragment, tag).addToBackStack(null).commit()


//        println("fragmentManager.fragments.get(0) = ${fragmentManager.findFragmentById(R.id.home)}")
//        val findFragmentById = fragmentManager.findFragmentById(R.id.home)
//        if (findFragmentById == null){}else {
//            println("findFragmentById.isVisible ======== ${findFragmentById!!.isVisible}")
//        }
    }

    override fun onStateUpdate(p0: InstallState) {
    }

//    override fun onDestroy() {
//        super.onDestroy()
////        myTimerNote!!.cancel()
//        appUpdateManager.unregisterListener(this)
//    }
//
//    override fun onResume() {
//        super.onResume()
//        appUpdateManager.appUpdateInfo.addOnSuccessListener { info ->
////            if (info.updateAvailability() == UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS) {
////                startUpdate(info)
////            }
//            if (currentType == AppUpdateType.IMMEDIATE) {
//                if (info.installStatus() == InstallStatus.DOWNLOADED)
//                    flexibleUpdateDownloadCompleted()
//            } else if (currentType == AppUpdateType.IMMEDIATE) {
//                if (info.updateAvailability() == UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS) {
//                    startUpdate(info)
//                }
//            }
//        }
//    }

//    private fun flexibleUpdateDownloadCompleted() {
//        Toast.makeText(
//            this@MainActivity,
//            "An update has just been downloaded.",
//            Toast.LENGTH_SHORT
//        ).show()
//        appUpdateManager.completeUpdate()
//    }
//
//    private fun startUpdate(appUpdateInfo: AppUpdateInfo) {
//        appUpdateManager.startUpdateFlowForResult(
//            appUpdateInfo, AppUpdateType.IMMEDIATE, this, MY_REQUEST_CODE
//        )
//    }


//    private fun checkForAnUpdate() {
//        appUpdateManager = AppUpdateManagerFactory.create(applicationContext)
//        val appUpdateInfoTask = appUpdateManager.appUpdateInfo
//        appUpdateInfoTask.addOnSuccessListener { appUpdateInfo ->
//            println("appUpdateInfo.updateAvailability() = ${appUpdateInfo.updateAvailability()}")
//            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
//                && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)
//            ) {
//                println("updation=====================")
//                startUpdate(appUpdateInfo)
//            }
//        }
//    }

//    override fun onStateUpdate(state: InstallState) {
//        if (state.installStatus() == InstallStatus.DOWNLOADED) {
//            flexibleUpdateDownloadCompleted()
//            appUpdateManager.unregisterListener(this)
//        }
//    }
}

//import android.os.Bundle
//import com.google.android.material.bottomnavigation.BottomNavigationView
//import androidx.appcompat.app.AppCompatActivity

//import androidx.navigation.findNavController
//import androidx.navigation.ui.AppBarConfiguration
//import androidx.navigation.ui.setupActionBarWithNavController
//import androidx.navigation.ui.setupWithNavController
//import com.example.cricketlivenz.databinding.ActivityMainBinding
//
//class MainActivity : AppCompatActivity() {
//
//    private lateinit var binding: ActivityMainBinding
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        val navView: BottomNavigationView = binding.navView
//
//        val navController = findNavController(R.id.nav_host_fragment_activity_main)
//        // Passing each menu ID as a set of Ids because each
//        // menu should be considered as top level destinations.
//        val appBarConfiguration = AppBarConfiguration(
//            setOf(
//                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
//            )
//        )
//        setupActionBarWithNavController(navController, appBarConfiguration)
//        navView.setupWithNavController(navController)
//    }
//}