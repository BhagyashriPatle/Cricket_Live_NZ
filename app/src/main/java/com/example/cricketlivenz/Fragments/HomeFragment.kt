package com.example.cricketlivenz.Fragments

import android.app.Dialog
import android.graphics.LinearGradient
import android.graphics.Shader
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

import com.example.cricketlivenz.databinding.FragmentHomeBinding

import java.util.Timer
import java.util.TimerTask


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    private lateinit var fragmentActivity: FragmentActivity
    private lateinit var timer5Resort: Timer
    private var current = 0
    private var currentMonthPosition = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        fragmentActivity = context as FragmentActivity

        return binding.root

    }
}

