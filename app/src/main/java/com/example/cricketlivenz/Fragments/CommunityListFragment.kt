package com.example.cricketlivenz.Fragments

import android.Manifest
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.LinearGradient
import android.graphics.Shader
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RadioGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.bumptech.glide.Glide
import com.example.cricketlivenz.databinding.FragmentCommunityListBinding

import java.io.File
import java.sql.DriverManager.println
import java.util.Timer
import java.util.TimerTask

class CommunityListFragment : Fragment() {
    private lateinit var binding: FragmentCommunityListBinding
    private lateinit var fragmentActivity: FragmentActivity
   // private lateinit var mySharedPreferences: MySharedPreferences
    private lateinit var cameraPermission: Array<String>
    private var hasBoughtCourse = false
    var imagesListFile: java.util.ArrayList<File>? = arrayListOf<File>()
    var imageFile: File? = null
    var myTimerNote: Timer? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCommunityListBinding.inflate(inflater, container, false)
        return binding.root
    }
}