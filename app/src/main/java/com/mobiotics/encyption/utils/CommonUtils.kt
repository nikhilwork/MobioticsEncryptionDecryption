package com.mobiotics.encyption.utils

import android.app.Activity
import android.view.inputmethod.InputMethodManager
import java.util.logging.LogManager

class CommonUtils {

    companion object{
        fun hideSoftKeyboard(activity: Activity?) {
            try {
                if (activity != null && activity.currentFocus != null) {
                    val inputMethodManager = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                    inputMethodManager.hideSoftInputFromWindow(activity.currentFocus!!.windowToken, 0)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}