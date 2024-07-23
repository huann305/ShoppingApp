package com.datn.shoppingapp.ui.base

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.datn.shoppingapp.R

abstract class BaseActivityNew<T : ViewDataBinding> : BaseActivityBlank() {
    abstract fun getLayoutRes(): Int
    abstract fun getDataFromIntent()
    abstract fun initData()
    abstract fun initView()
    abstract fun setListener()
    lateinit var binding: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        doFirstMethod()
        binding = DataBindingUtil.setContentView(this, getLayoutRes())
        afterSetContentView()
        getDataFromIntent()
        initData()
        initView()
        setListener()

        enableEdgeToEdge()

    }

    open fun doFirstMethod() {
    }

    open fun afterSetContentView() {
    }

    fun showToast(mess: String?) {
        Toast.makeText(this, mess, Toast.LENGTH_SHORT).show()
    }
}
