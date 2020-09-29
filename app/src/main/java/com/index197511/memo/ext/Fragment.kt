package com.index197511.memo.ext

import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment

fun Fragment.closeKeyboard() {
    activity
        ?.getSystemService(Context.INPUT_METHOD_SERVICE)
        ?.let { it as? InputMethodManager }
        ?.hideSoftInputFromWindow(view?.windowToken ?: return, 0)
}
