package com.example.cricketlivenz.Fragments

import android.app.AlertDialog
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.finishAffinity
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.cricketlivenz.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
//        binding.accDetails.setOnClickListener {
//            // requireContext().startActivity(Intent(context, AccountDetails::class.java))
//        }
//        binding.userName.text = userData!!.name
//        binding.userEmail.text = userData.email
//        Glide.with(requireActivity()).load(userData.image).placeholder(R.drawable.animated_couple)
//            .into(binding.userLogo)


//        initSideViews()


//        binding.logout.setOnClickListener {
//
//            val alertDialog: AlertDialog = AlertDialog.Builder(context) //set icon
//                //.setIcon(R.drawable.logout) //set title
//                .setTitle("Alert!") //set message
//                .setMessage("Are you sure you want to logout?") //set positive button
//                .setPositiveButton(
//                    "Yes"
//                ) { dialogInterface, i -> //set what would happen when positive button is clicked
//                    mySharedPreferences.clearAll()
//                    mySharedPreferences.setUserModel(null)
//                    val intent = Intent(context, SignIn::class.java)
//                    intent.putExtra("arriving_after", "logout")
//                    startActivity(intent)
//                    finishAffinity(requireActivity())
//
//                } //set negative button
//                .setNegativeButton(
//                    "No"
//                ) { dialogInterface, i -> //set what should happen when negative button is clicked
//                    dialogInterface.dismiss()
//                }
//                .show()
//
//
//        }

        /*
        binding.backBtn.setOnClickListener {

        val homeFragment=HomeFragment()
        requireActivity().supportFragmentManager.beginTransaction().replace(((view as ViewGroup).parent as View).id,homeFragment,"backToHome")
        .addToBackStack(null)
        .commit()

        }
        Inflate the layout for this fragment
        */
        return binding.root
    }

//    private fun initSideViews() {
//
//
//        binding.navMenu.logout.setOnClickListener(object :
//            View.OnClickListener {
//            override fun onClick(v: View?) {
//                showLogoutDialog()
//            }
//
//        })
//
//        binding.navMenu.accDetails.setOnClickListener {
//            val intent = Intent(requireActivity(), AccountDetails::class.java)
//            startActivity(intent)
//        }
//
//        binding.navMenu.feedbackParent.setOnClickListener {
//            val intent = Intent(requireActivity(), AddFeedBackActivity::class.java)
//            startActivity(intent)
//        }
//        binding.navMenu.listPropertyParent.setOnClickListener {
//            val intent = Intent(requireActivity(), PropertyListActivity::class.java)
//            startActivity(intent)
//        }
//
//        binding.navMenu.privPolicy.setOnClickListener {
//            val intent = Intent(requireActivity(), PrivacyPolicyActivity::class.java)
//            startActivity(intent)
//        }
//
//        binding.navMenu.tAndC.setOnClickListener(object :
//            View.OnClickListener {
//            override fun onClick(v: View?) {
//                val intent =
//                    Intent(requireActivity(), TermsAndConditionActivity::class.java)
//                startActivity(intent)
//            }
//        })
//
//        binding.navMenu.rateApp.setOnClickListener {
//
//            val packageName = activity?.packageName
//            try {
//                startActivity(
//                    Intent(
//                        Intent.ACTION_VIEW,
//                        Uri.parse("market://details?id=$packageName")
//                    )
//                )
//            } catch (anfe: ActivityNotFoundException) {
//                startActivity(
//                    Intent(
//                        Intent.ACTION_VIEW,
//                        Uri.parse("https://play.google.com/store/apps/details?id=$packageName")
//                    )
//                )
//            }
//
//        }
//
//        binding.navMenu.settings.setOnClickListener(object :
//            View.OnClickListener {
//            override fun onClick(v: View?) {
//                val intent = Intent(requireActivity(), SettingActivity::class.java)
//                startActivity(intent)
//            }
//        })
//
//        binding.navMenu.refundNCanc.setOnClickListener(object :
//            View.OnClickListener {
//            override fun onClick(v: View?) {
//                val intent = Intent(requireActivity(), RefundAndCancelActivity::class.java)
//                startActivity(intent)
//            }
//        })
//
//        binding.navMenu.aboutUs.setOnClickListener(object :
//            View.OnClickListener {
//            override fun onClick(v: View?) {
//                val intent = Intent(requireActivity(), AboutUsActivity::class.java)
//                startActivity(intent)
//            }
//        })
//
//        binding.navMenu.myWallet.setOnClickListener(object :
//            View.OnClickListener {
//            override fun onClick(v: View?) {
//                val intent = Intent(requireActivity(), WalletActivity::class.java)
//                startActivity(intent)
//            }
//        })
//
//        binding.navMenu.friendRefer.setOnClickListener(object :
//            View.OnClickListener {
//            override fun onClick(v: View?) {
//                val intent = Intent(requireActivity(), ReferAndEarnActivity::class.java)
//                startActivity(intent)
//            }
//        })
//
//        binding.userName.text = mySharedPreferences.userObjectModel!!.name
////        Glide.with(requireActivity())
////            .load("userLogo")
////            .apply(RequestOptions.placeholderOf(R.drawable.user))
////            .into(binding.userLogo)
//
//        binding.navMenu.lnrBooked.setOnClickListener(View.OnClickListener {
//            val intent = Intent(requireActivity(), BookedActivity::class.java)
//            startActivity(intent)
//        })
//
////        mySharedPreferences = MySharedPreferences.getInstance(this)!!
////        activity.navigationMenu.userName.text =  mySharedPreferences.userObjectModel!!.name
////        Glide.with(MainActivity()).load( mySharedPreferences.userObjectModel!!.image).placeholder(R.drawable.kareena)
////            .into(activity.navigationMenu.userLogo)
//
//
//    }


}