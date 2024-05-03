package com.sanhuzhen.maydayassessment.ui

import android.app.AlertDialog
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.sanhuzhen.maydayassessment.databinding.FragmentFocusBinding

class FocusFragment: Fragment() {
    private lateinit var countDownTimer: CountDownTimer
    private var window: Window? = null
    private val binding: FragmentFocusBinding by lazy {
        FragmentFocusBinding.inflate(layoutInflater)
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
        // 初始化倒计时
        val totalTimeInMillis: Long = 100 // 倒计时总时长，这里是 60 秒
        val intervalInMillis: Long = 1000 // 倒计时间隔，这里是每秒钟更新一次
        window = requireActivity().window
        countDownTimer = object : CountDownTimer(totalTimeInMillis, intervalInMillis) {
            override fun onTick(millisUntilFinished: Long) {
                val secondsLeft = millisUntilFinished / 1000
                binding.countdownTextView.text = "倒计时：$secondsLeft 秒"
            }

            override fun onFinish() {
                binding.countdownTextView.text = "倒计时结束"
                showAlertDialog()
                // 在倒计时结束后，重新启用屏幕触
                window?.clearFlags(android.view.WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
            }
        }

        // 开始按钮点击事件
        binding.startButton.setOnClickListener {
            AlertDialog.Builder(this.requireContext())
                .setTitle("确认要开启吗")
                .setMessage("开启后屏幕动不了哟")
                .setPositiveButton("确定") { _, _ ->
                    // 在开始倒计时前，禁用屏幕触摸
                    window?.addFlags(android.view.WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
                    // 启动倒计时
                    countDownTimer.start()
                }
                .setNegativeButton("取消", null)
                .show()

        }



    }
    private fun showAlertDialog() {
        val alertDialogBuilder = AlertDialog.Builder(this.requireContext())
        alertDialogBuilder.setTitle("恭喜你完成了！")
        alertDialogBuilder.setMessage("你获得了10个积分！")
        alertDialogBuilder.setPositiveButton("确定") { dialog, which ->
            // 确定按钮点击事件，可以执行相应的操作
            dialog.dismiss() // 关闭对话框
        }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }
}
