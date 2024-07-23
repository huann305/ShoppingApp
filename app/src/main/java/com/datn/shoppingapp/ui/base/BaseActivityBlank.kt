package com.datn.shoppingapp.ui.base

import android.view.View
import androidx.appcompat.app.AppCompatActivity

open class BaseActivityBlank : AppCompatActivity() {
    fun back(view: View) {
        finish()
    }
}
