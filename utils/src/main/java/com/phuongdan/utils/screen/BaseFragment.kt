package com.danphuong.utils.screen

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.danphuong.utils.dialog.DialogUtils

abstract class BaseFragment<VB : ViewBinding> : Fragment() {

    private var _binding: VB? = null
    val binding get() = _binding!!
    var mLoadingDialog: AlertDialog? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflateViewBinding(inflater, container)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true);
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        mLoadingDialog = activity?.let { DialogUtils.showProgressDialog(it) }
    }

    fun setLoadingDialog(alertDialog : AlertDialog) {
        mLoadingDialog = alertDialog
    }

    fun showLoading() {
        if (isAdded) {
        mLoadingDialog?.show()
        }
    }

    fun hideLoading() {
        if (isAdded) {
            mLoadingDialog?.hide()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onPause() {
        super.onPause()
    }

    abstract fun inflateViewBinding(inflater: LayoutInflater, container: ViewGroup?): VB
    abstract fun init()
}