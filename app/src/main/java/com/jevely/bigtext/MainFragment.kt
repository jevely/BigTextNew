package com.jevely.bigtext

import android.content.pm.ActivityInfo
import android.content.res.Resources.getSystem
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_main.*

/**
 * 主界面
 */
class MainFragment : Fragment() {

    private lateinit var textViewModel: TextViewModel

    // 90 ~ 150
    private var textSize: Float = 0F

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        Log.d("LJW","mainfragment")

        requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        requireActivity().window.setSoftInputMode(
            WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE
        )

        textViewModel = ViewModelProvider(requireActivity()).get(TextViewModel::class.java)

        textSize = SharedTool.getInstance().getInt().toFloat()

        main_edit.textSize = textSize

        setLiveDataListener()
        setEditListener()
        seekBarListener()
    }

    override fun onResume() {
        super.onResume()
        main_edit.setText("")
    }

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }

    private fun setLiveDataListener() {
        textViewModel.getTextSizeLiveData().observe(viewLifecycleOwner, Observer<Int> { result ->
            // update UI
            main_edit.textSize = result.toFloat()
        })


    }

    private fun setEditListener() {
        main_edit.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_GO) {
                val editResult = main_edit.text?.trim().toString()
                if (!TextUtils.isEmpty(editResult)) {
                    if (getSystem().displayMetrics.scaledDensity * textSize != main_edit.textSize) {
                        SharedTool.getInstance().putInt(textViewModel.getTextSizeLiveData().value!!)
                    }
                    textViewModel.getTextLiveData().value = editResult
                    findNavController().navigate(R.id.action_mainFragment_to_showFragment)
                }
            }
            true
        }
    }

    private fun seekBarListener() {

        val seekBarProgress = (SharedTool.getInstance().getInt() - 90) / 0.6F
        main_seekbar.progress = seekBarProgress.toInt()

        main_seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val resultProgress = progress * 0.6 + 90
                textViewModel.getTextSizeLiveData().value = resultProgress.toInt()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })
    }
}