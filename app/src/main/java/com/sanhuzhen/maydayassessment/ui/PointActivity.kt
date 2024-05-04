package com.sanhuzhen.maydayassessment.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.sanhuzhen.maydayassessment.R
import com.sanhuzhen.maydayassessment.adapter.PointRvAdapter
import com.sanhuzhen.maydayassessment.base.BaseActivity
import com.sanhuzhen.maydayassessment.bean.award
import com.sanhuzhen.maydayassessment.databinding.ActivityPointBinding


class PointActivity: BaseActivity() {
    private val awardList = ArrayList<award>()
    private val binding: ActivityPointBinding by lazy {
        ActivityPointBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        Log.d("PointActivity", this.javaClass.simpleName)
        initEvent()
        addAware()
        val layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        binding.rvAware.layoutManager = layoutManager
        val adapter = PointRvAdapter(awardList)
        binding.rvAware.adapter = adapter
    }
    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        initEvent()
    }
    private fun initEvent() {
        val sanSharedPreferences: SharedPreferences = getSharedPreferences("point", Context.MODE_PRIVATE)
        val point: Int = sanSharedPreferences.getInt("point", 0)
        binding.tvPoint.text = point.toString()
        binding.btnBack.setOnClickListener {
            finish()
        }
    }
    private fun addAware() {
        awardList.apply{
            add(award(R.drawable.img_12,"橡皮擦","10"))
            add(award(R.drawable.img_6,"圆珠笔","100"))
            add(award(R.drawable.img_7,"笔记本","200"))
            add(award(R.drawable.img_8,"卷笔刀","50"))
            add(award(R.drawable.img_9,"笔盒","200"))
            add(award(R.drawable.img_10,"笔筒","100"))
            add(award(R.drawable.img_11,"蜡笔","500"))
        }
    }
}