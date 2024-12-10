//package com.likesby.tradr.Fragments
//
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.TextView
//import androidx.fragment.app.Fragment
//import com.ensivo.tradr.R
//import com.ensivo.tradr.databinding.FragmentQnaBinding
//import com.google.android.material.tabs.TabLayout
//import com.google.android.material.tabs.TabLayoutMediator
//import com.likesby.tradr.Adapter.TopQnaTabAdapter
//
//class QnaFragment : Fragment() {
//    private lateinit var binding: FragmentQnaBinding
//
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View {
//        binding = FragmentQnaBinding.inflate(inflater, container, false)
//        initData()
//        return binding.root
//    }
//
//    private fun initData() {
//        setTablayoutItems()
//    }
//
//    private fun setTablayoutItems() {
//        binding.tabLayout.tabGravity = TabLayout.GRAVITY_CENTER
//        val adapter = TopQnaTabAdapter(childFragmentManager, lifecycle)
//        binding.viewPager.adapter = adapter
//        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
//            when (position) {
//                0 -> {
//                    val tab1: View = layoutInflater.inflate(R.layout.custom_top_qna_tab_view, null)
//                    val tab1Text = tab1.findViewById<TextView>(R.id.category_name)
//                    tab1Text.text = getString(R.string.equity_analyst)
//                    tab.customView = tab1
//                }
//
//                1 -> {
//                    val tab2: View = layoutInflater.inflate(R.layout.custom_top_qna_tab_view, null)
//                    val tab2Text = tab2.findViewById<TextView>(R.id.category_name)
//                    tab2Text.text = getString(R.string.ai_system)
//                    tab.customView = tab2
//                }
//
//                2 -> {
//                    val tab3: View = layoutInflater.inflate(R.layout.custom_top_qna_tab_view, null)
//                    val tab3Text = tab3.findViewById<TextView>(R.id.category_name)
//                    tab3Text.text = getString(R.string.logic_based_questions)
//                    tab.customView = tab3
//                }
//
//                3 -> {
//                    val tab4: View = layoutInflater.inflate(R.layout.custom_top_qna_tab_view, null)
//                    val tab4Text = tab4.findViewById<TextView>(R.id.category_name)
//                    tab4Text.text = getString(R.string.basics)
//                    tab.customView = tab4
//                }
//
//            }
//        }.attach()
//        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
//            override fun onTabSelected(tab: TabLayout.Tab) {
//                tab.customView?.isSelected = true
//            }
//            override fun onTabUnselected(tab: TabLayout.Tab) {
//                tab.customView?.isSelected = false
//            }
//            override fun onTabReselected(tab: TabLayout.Tab) {}
//        })
//
//    }
//}