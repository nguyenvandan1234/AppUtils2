package com.danphuong.utils.dialog

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.phuongdan.utils.R

object DialogUtils {
    fun showTwoButtonDialog(
    activity: Activity,
    title: String,
    content: String,
    okTextButton: String,
    cancelTextButton: String,
    onClick: DialogInterface.OnClickListener,
    onCancelClick: DialogInterface.OnClickListener
): Dialog {
    var dialog = AlertDialog.Builder(activity)
        .setTitle(title)
        .setMessage(content) // Specifying a listener allows you to take an action before dismissing the dialog.
        // The dialog is automatically dismissed when a dialog button is clicked.
        .setPositiveButton(cancelTextButton, onClick) // A null listener allows the button to dismiss the dialog and take no further action.
        .setNegativeButton(okTextButton, onCancelClick)
        .setCancelable(false)
        .show()
    return dialog
}

    fun showTwoButtonDialog(
        activity: Activity,
        content: String,
        okTextButton: String,
        cancelTextButton: String,
        onClick: DialogInterface.OnClickListener,
        onCancelClick: DialogInterface.OnClickListener
    ): Dialog {
        var dialog = AlertDialog.Builder(activity)
            .setMessage(content) // Specifying a listener allows you to take an action before dismissing the dialog.
            // The dialog is automatically dismissed when a dialog button is clicked.
            .setPositiveButton(cancelTextButton, onClick) // A null listener allows the button to dismiss the dialog and take no further action.
            .setNegativeButton(okTextButton, onCancelClick)
            .setCancelable(false)
            .show()
        return dialog
    }

    fun showOkDialog(
        activity: Activity,
        title: String,
        content: String,
        okTextButton: String,
        onClick: DialogInterface.OnClickListener,
    ): Dialog {
        var dialog = AlertDialog.Builder(activity)
            .setTitle(title)
            .setMessage(content) // Specifying a listener allows you to take an action before dismissing the dialog.
            // The dialog is automatically dismissed when a dialog button is clicked.
            .setPositiveButton(okTextButton, onClick) // A null listener allows the button to dismiss the dialog and take no further action.
            .setCancelable(false)
            .show()
        return dialog
    }

    fun showOkDialog(
        activity: Activity,
        content: String,
        okTextButton: String,
        onClick: DialogInterface.OnClickListener,
    ): Dialog {
        var dialog = AlertDialog.Builder(activity)
            .setMessage(content) // Specifying a listener allows you to take an action before dismissing the dialog.
            // The dialog is automatically dismissed when a dialog button is clicked.
            .setPositiveButton(okTextButton, onClick) // A null listener allows the button to dismiss the dialog and take no further action.
            .setCancelable(false)
            .show()
        return dialog
    }

    fun showProgressDialog(activity: Activity, title: String = "", content: String = "", backgroundColor: ColorDrawable = ColorDrawable(Color.TRANSPARENT)): AlertDialog? {
        var builder = AlertDialog.Builder(activity);
        var inflater = (activity).getLayoutInflater();
        builder.setTitle(title);
        builder.setCancelable(false);
        var dialogView = inflater.inflate(R.layout.loading_dialog_layout, null)
        builder.setView(dialogView)

        var tvLoading = dialogView.findViewById<TextView>(R.id.tv_loading_dialog_content)
        if (TextUtils.isEmpty(content)) {
            tvLoading.visibility = View.GONE
        } else {
            tvLoading.visibility = View.VISIBLE
            tvLoading.text = content
        }
        var dialog = builder.create();
        dialog.window?.setBackgroundDrawable(backgroundColor)
        return dialog
    }
}