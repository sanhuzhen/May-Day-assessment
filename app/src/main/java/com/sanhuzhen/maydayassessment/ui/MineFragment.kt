package com.sanhuzhen.maydayassessment.ui

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.sanhuzhen.maydayassessment.databinding.FragmentMineBinding
import com.sanhuzhen.maydayassessment.utils.toast

/**
 * @author: sanhuzhen
 * @date: 2024/05/01 17:07
 * @description: 个人中心
 */

@Suppress("NAME_SHADOWING")
class MineFragment: Fragment() {
    private lateinit var mSharedPreferences: SharedPreferences
    private val binding: FragmentMineBinding by lazy {
        FragmentMineBinding.inflate(layoutInflater)
    }
    //设置请求码
    companion object{
        const val REQUEST_CODE_CHOOSE_PHOTO = 1
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mSharedPreferences = this.requireActivity().getPreferences(Context.MODE_PRIVATE)

        initEvent()
        Persistence()
    }
    //初始化事件
    private fun initEvent() {
        binding.btMineWe.setOnClickListener {
            this.context?.let { it1 -> toast(it1,"功能暂未开放") }
        }
        binding.btMineFeedback.setOnClickListener {
            startActivity(Intent(this.context,FeedBackActivity::class.java))
        }
        binding.ivMineHead.setOnClickListener {
            //打开相册路径
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            intent.addCategory(Intent.CATEGORY_OPENABLE)
            //指定只显示图片
            intent.type = "image/*"
            startActivityForResult(intent, REQUEST_CODE_CHOOSE_PHOTO)
        }
        binding.btMinePoint.setOnClickListener {
            startActivity(Intent(this.context,PointActivity::class.java))
        }
        binding.tvMineName.setOnClickListener {
            val dialog = AlertDialog.Builder(this.requireActivity())
            val editText = EditText(this.requireActivity())
            editText.hint = "请输入姓名"
            dialog.setView(editText)
            dialog.setPositiveButton("确定") { _, _ ->
                val name = editText.text.toString()
                binding.tvMineName.text = name
                mSharedPreferences.edit()?.putString("name", name)?.apply()
            }
            dialog.setNegativeButton("取消") { dialog, _ ->
                dialog.dismiss()
            }
            dialog.show()
        }
    }
    //权限申请
    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            REQUEST_CODE_CHOOSE_PHOTO -> {
                if (resultCode == Activity.RESULT_OK && data != null){
                    data.data?.let {uri ->
                        //将选择照片展示
                        val bitmap = getBitmapFromUri(uri)
                        binding.ivMineHead.setImageBitmap(bitmap)
                        mSharedPreferences.edit()?.putString("head",uri.toString())?.apply()
                    }
                }
            }
        }
    }
    private fun getBitmapFromUri(uri: android.net.Uri) = this.requireActivity().contentResolver.
             openInputStream(uri)?.use {
        BitmapFactory.decodeStream(it)
    }
    //持久化
    private fun Persistence() {
        // 持久化头像
//        val headUri = mSharedPreferences.getString("head", null)
//        if (headUri != null) {
//            val bitmap = getBitmapFromUri(Uri.parse(headUri))
//            if (bitmap != null) {
//                binding.ivMineHead.setImageBitmap(bitmap)
//            }
//        }
        // 持久化姓名
        // 持久化姓名
        val name = mSharedPreferences.getString("name", null)
        if (name != null) {
            binding.tvMineName.text = name
        }
    }


}