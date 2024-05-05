package com.sanhuzhen.maydayassessment.adapter

import android.os.Parcelable
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.sanhuzhen.maydayassessment.ui.AssignmentFragment
import com.sanhuzhen.maydayassessment.ui.FocusFragment
import com.sanhuzhen.maydayassessment.ui.MineFragment

/**
 * @author: sanhuzhen
 * @date: 2024/05/01 13:23
 * @description:建立ViewPager2的适配器
 */

open class ViewPageAdapter(fa: FragmentActivity): FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment =
        when(position){
            0-> AssignmentFragment()
            1-> FocusFragment()
            2->MineFragment()
            else-> error("Fragment Error")
        }
    }

