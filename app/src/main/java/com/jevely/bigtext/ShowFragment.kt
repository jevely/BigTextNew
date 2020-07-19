package com.jevely.bigtext

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_show.*


/**
 * 展示界面
 */
class ShowFragment : Fragment() {

    private lateinit var textViewModel: TextViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_show, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        Log.d("LJW","showFragment")

        requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

        textViewModel = ViewModelProvider(requireActivity()).get(TextViewModel::class.java)

        show_tv.textSize = SharedTool.getInstance().getInt().toFloat()

        textViewModel.getTextLiveData().observe(viewLifecycleOwner, Observer<String> { result ->
            // update UI
            show_tv.text = result
        })

        setLongListener()
    }

    companion object {
        @JvmStatic
        fun newInstance() = ShowFragment()
    }

    private fun setLongListener() {
        show_tv.setOnLongClickListener {
            findNavController().popBackStack()
        }
    }
}