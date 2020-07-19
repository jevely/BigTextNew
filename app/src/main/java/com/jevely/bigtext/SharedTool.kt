package com.jevely.bigtext

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

class SharedTool {

    companion object {

        const val TEXT_SIZE = "TEXT_SIZE"

        // For Singleton instantiation
        @Volatile
        private var instance: SharedTool? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: SharedTool().also { instance = it }
            }

        fun destroy() {
            instance == null
        }
    }

    private val sharedPreferences: SharedPreferences
    private val editor: SharedPreferences.Editor

    init {
        sharedPreferences =
            TextApplication.getContext().getSharedPreferences("text", Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
    }

    fun putInt(textSize: Int) {
        sharedPreferences.edit(commit = true) { putInt(TEXT_SIZE, textSize) }
    }

    fun getInt(): Int {
        return sharedPreferences.getInt(TEXT_SIZE, 90)
    }

}