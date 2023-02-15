package com.phuongdan.apputils

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.danphuong.utils.dialog.DialogUtils

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        DialogUtils.showProgressDialog(this)?.show()
    }
}