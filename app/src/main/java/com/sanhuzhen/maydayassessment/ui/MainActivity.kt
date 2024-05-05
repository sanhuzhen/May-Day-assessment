package com.sanhuzhen.maydayassessment.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.sanhuzhen.maydayassessment.R
import com.sanhuzhen.maydayassessment.adapter.ViewPageAdapter
import com.sanhuzhen.maydayassessment.base.BaseActivity
import com.sanhuzhen.maydayassessment.databinding.ActivityMainBinding

/**
 * @author: sanhuzhen
 * @date: 2024/05/01 13：19
 * @description:实现底部导航栏
 */
class MainActivity : BaseActivity() {
    private val fragmentList: MutableList<Fragment> = mutableListOf()
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        aadFragment()
        binding.viewPager2.adapter = ViewPageAdapter(this)
        binding.viewPager2.registerOnPageChangeCallback(object :
            androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.navView.menu.getItem(position).isChecked = true
            }
        })
        //当nav图标变换时，viewpage2页面也变化
        binding.navView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_task -> binding.viewPager2.currentItem = 0
                R.id.nav_focus -> binding.viewPager2.currentItem = 1
                R.id.nav_mine -> binding.viewPager2.currentItem = 2
            }
            return@setOnItemSelectedListener true
        }
        //禁止页面之间的滑动
        binding.viewPager2.isUserInputEnabled = false
    }
    private fun aadFragment() {
        fragmentList.apply {
            add(AssignmentFragment())
            add(FocusFragment())
            add(MineFragment())
        }
    }
}
