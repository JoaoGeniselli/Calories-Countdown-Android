package com.jgeniselli.caloriescountdown

import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.databinding.BindingAdapter

interface OnTextChanged {
    fun onTextChanged(text: String?)
}

@BindingAdapter("app:onTextChanged")
fun setOnTextChangedListener(editText: EditText, listener: OnTextChanged) {
    editText.addTextChangedListener {
        val updatedContent = it?.toString()
        listener.onTextChanged(updatedContent)
    }
}
