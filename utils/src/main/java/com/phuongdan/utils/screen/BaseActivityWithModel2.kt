package com.danphuong.utils.screen

import android.app.AlertDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.danphuong.utils.dialog.DialogUtils

abstract class BaseActivityWithModel2<VM : ViewModel, B : ViewBinding>(private val viewModelClass: Class<VM>) : AppCompatActivity() {

    lateinit var viewModel: VM
    lateinit var binding: B
    var mLoadingDialog: AlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(viewModelClass)
        binding = getViewBinding()
        setContentView(binding.root)
        initView()
        initObserver()
        mLoadingDialog = DialogUtils.showProgressDialog(this)
    }

    fun setLoadingDialog(alertDialog: AlertDialog) {
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
    abstract fun initObserver()}
