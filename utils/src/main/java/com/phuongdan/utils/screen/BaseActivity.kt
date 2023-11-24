package com.danphuong.utils.screen

import android.app.AlertDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.danphuong.utils.dialog.DialogUtils

abstract class BaseActivity<B : ViewBinding> : AppCompatActivity() {

    lateinit var binding: B
    var mLoadingDialog: AlertDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewBinding()
        setContentView(binding.root)
        initView()
        initObserver()
        mLoadingDialog = DialogUtils.showProgressDialog(this)
    }

    fun setLoadingDialog(alertDialog : AlertDialog) {
        mLoadingDialog = alertDialog
    }

    fun showLoading() {
        mLoadingDialog?.show()
    }

    fun hideLoading() {
        mLoadingDialog?.hide()
    }
    abstract fun getViewBinding(): B

    abstract fun initView()
    abstract fun initObserver()

}