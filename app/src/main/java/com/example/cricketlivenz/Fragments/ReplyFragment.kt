//package com.likesby.tradr.Fragments
//
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.databinding.DataBindingUtil
//import com.ensivo.tradr.R
//import com.ensivo.tradr.databinding.FragmentReplyBinding
//import com.google.android.material.bottomsheet.BottomSheetDialogFragment
//import com.likesby.tradr.Model.ChatModel
//
//class ReplyFragment : BottomSheetDialogFragment() {
////    companion object {
////        fun newInstance(originalMessage: String): ReplyFragment {
////            val fragment = ReplyFragment()
////            val args = Bundle()
////            args.putParcelable("originalMessage", originalMessage)
////            fragment.arguments = args
////            return fragment
////        }
////    }
//private lateinit var binding :FragmentReplyBinding
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        binding =DataBindingUtil.inflate(inflater,R.layout.fragment_reply, container, false)
//
////        val originalMessage = arguments?.getParcelable<ChatMessage>("originalMessage")
//
//        // Initialize UI elements and set click listeners
//
//        // Handle the "Send" button click
//        binding.sendButton.setOnClickListener {
//            val replyText = binding.replyEditText.text.toString()
////            if (originalMessage != null) {
////                // Send the reply via an API call
////                sendReplyToAPI(originalMessage, replyText)
////            }
//            dismiss()
//        }
////
////        // Handle the "Close" button click
////        closeButton.setOnClickListener {
////            dismiss()
////        }
//
//        return binding.root
//    }
//}
