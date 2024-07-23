package com.datn.shoppingapp.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction

abstract class BaseFragment<T : ViewDataBinding> : Fragment() {
    abstract val layoutRes: Int

    abstract fun initView()
    abstract fun initData()
    abstract fun initEvent()

    abstract val frame: Int

    protected var mActivity: AppCompatActivity? = null

    lateinit var binding: T
        private set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (activity !is AppCompatActivity) {
            Throwable("Activity no override BaseActivity")
        }
        mActivity = activity as AppCompatActivity?
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindView(inflater, container, layoutRes)
        return binding!!.root
    }

    fun bindView(inflater: LayoutInflater?, viewGroup: ViewGroup?, res: Int) {
        binding = DataBindingUtil.inflate(
            inflater!!, res, viewGroup, false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initView()
        initEvent()
    }

    fun addFragment(fragment: BaseFragment<*>, frame: Int) {
        val frm = mActivity!!.supportFragmentManager
        frm.beginTransaction()
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .add(frame, fragment)
            .addToBackStack(fragment.javaClass.simpleName).commit()
    }

    fun addFragment(fragment: BaseFragment<*>) {
        val frm = mActivity!!.supportFragmentManager
        frm.beginTransaction()
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .add(frame, fragment)
            .addToBackStack(fragment.javaClass.simpleName).commit()
    }

    fun addFragmentWithTag(fragment: BaseFragment<*>, frame: Int, tag: String?) {
        val frm = mActivity!!.supportFragmentManager
        frm.beginTransaction()
            .add(frame, fragment, tag)
            .addToBackStack(fragment.javaClass.simpleName)
            .commit()
    }

    fun showToast(mess: String?) {
        Toast.makeText(mActivity, mess, Toast.LENGTH_SHORT).show()
    }
}
