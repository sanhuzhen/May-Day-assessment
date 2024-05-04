package com.sanhuzhen.maydayassessment.ui

import android.app.AlertDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.Fragment
import com.sanhuzhen.maydayassessment.databinding.FragmentFocusBinding
import java.util.Calendar

/**
 * @author sanhuzhen
 * @date 2024/5/4 13:01
 * @description: 专注
 */

class FocusFragment : Fragment() {
    private lateinit var countDownTimer: CountDownTimer
    private var window: Window? = null
    private var totalTimeInMillis: Long = 0L
    private val intervalInMillis: Long = 1000 // 倒计时间隔，这里是每秒钟更新一次
    private val binding: FragmentFocusBinding by lazy {
        FragmentFocusBinding.inflate(layoutInflater)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
    }

    private fun setupListeners() {
        binding.countdownTextView.setOnClickListener {
            showTimePickerDialog()
        }

        binding.startButton.setOnClickListener {
            showConfirmationDialog()
        }
    }

    private fun startCountdown() {
        countDownTimer = object : CountDownTimer(totalTimeInMillis, intervalInMillis) {
            override fun onTick(millisUntilFinished: Long) {
                val selectedHour = millisUntilFinished / 1000 / 60 /60
                val selectedMinute = (millisUntilFinished - (selectedHour * 60 * 60 * 1000)) / 1000 / 60
                val secondsLeft = (millisUntilFinished - (selectedHour * 60 * 60 * 1000)- (selectedMinute * 60 * 1000)) / 1000
                binding.countdownTextView.text = "倒计时：$selectedHour:$selectedMinute:$secondsLeft"
            }

            override fun onFinish() {
                binding.countdownTextView.text = "倒计时结束"
                showAlertDialog()
                window?.clearFlags(android.view.WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
            }
        }
        countDownTimer.start()
    }

    private fun showConfirmationDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle("确认要开启吗")
            .setMessage("开启后屏幕动不了哟")
            .setPositiveButton("确定") { _, _ ->
                window?.addFlags(android.view.WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
                startCountdown()
            }
            .setNegativeButton("取消", null)
            .show()
    }

    private fun showAlertDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle("恭喜你完成了！")
            .setMessage("你获得了10个积分！")
            .setPositiveButton("确定") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    private fun showTimePickerDialog() {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)
        val timePickerDialog = TimePickerDialog(requireContext(), TimePickerDialog.OnTimeSetListener { _, selectedHour, selectedMinute ->
            binding.countdownTextView.text = "倒计时：$selectedHour:$selectedMinute"
            totalTimeInMillis = (selectedHour * 60 + selectedMinute) * 60 * 1000L
        }, hour, minute, true)
        timePickerDialog.show()
    }
}


