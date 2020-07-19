package com.jevely.bigtext

import android.content.Context
import android.util.AttributeSet
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputConnection
import android.widget.EditText
import kotlinx.android.synthetic.main.fragment_main.*

/**
 * 自定义edittext
 */
class ActionEdittext(context: Context?, attrs: AttributeSet?) : EditText(context, attrs) {


    init {
        findFocus()
        requestFocus()
    }

    override fun onCreateInputConnection(outAttrs: EditorInfo?): InputConnection? {

        val connection = super.onCreateInputConnection(outAttrs) ?: return null

        outAttrs?.imeOptions = EditorInfo.IME_ACTION_GO

        return connection;

    }

}