package com.jevely.bigtext

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel

class TextViewModel : ViewModel() {

    private val textLiveData = MediatorLiveData<String>()

    private val textSize = MediatorLiveData<Int>()

    fun getTextLiveData(): MediatorLiveData<String> {
        return textLiveData
    }

    fun getTextSizeLiveData(): MediatorLiveData<Int> {
        return textSize
    }

}