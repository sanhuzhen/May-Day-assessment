package com.sanhuzhen.maydayassessment.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.sanhuzhen.maydayassessment.databinding.FragmentMineBinding
import com.sanhuzhen.maydayassessment.utils.toast

class MineFragment: Fragment() {
    private val binding: FragmentMineBinding by lazy {
        FragmentMineBinding.inflate(layoutInflater)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initEvent()
    }
    private fun initEvent() {
        binding.btMineWe.setOnClickListener {
            this.context?.let { it1 -> toast(it1,"功能暂未开放") }
        }
        binding.btMineFeedback.setOnClickListener {
            startActivity(Intent(this.context,FeedBackActivity::class.java))
        }
    }
}