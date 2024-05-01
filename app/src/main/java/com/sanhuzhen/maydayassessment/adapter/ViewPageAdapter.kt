package com.sanhuzhen.maydayassessment.adapter

import android.os.Parcelable
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * @author: sanhuzhen
 * @date: 2024/05/01 13:23
 * @description:建立ViewPager2的适配器
 */

open class ViewPageAdapter(fa: FragmentActivity, private val fragmentList: MutableList<Fragment>): FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = fragmentList.size

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }

}