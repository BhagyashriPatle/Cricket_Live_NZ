package com.example.cricketlivenz.Fragments

import android.graphics.LinearGradient
import android.graphics.Shader
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.bumptech.glide.Glide

import com.example.cricketlivenz.databinding.FragmentStrategyBinding
import com.google.android.material.tabs.TabLayout



class StrategyFragment : Fragment() {
    private lateinit var binding: FragmentStrategyBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStrategyBinding.inflate(inflater, container, false)

        return binding.root
    }
}