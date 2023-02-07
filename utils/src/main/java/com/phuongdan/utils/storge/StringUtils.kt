package com.phuongdan.utils.storge

import android.content.Context
import android.content.res.Resources
import android.graphics.Typeface
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.TextUtils
import android.text.style.TextAppearanceSpan
import android.util.Log
import android.widget.TextView
import java.security.MessageDigest


object StringUtils {
    fun getStringByIdName(context: Context, idName: String?): String {
        val res: Resources = context.getResources()
        return res.getString(res.getIdentifier(idName, "string", context.getPackageName()))
    }

    fun setFontText(context: Context , text : String , subText : String , styleText: Int,styleSubText: Int) : TextView? {
        val tv = TextView(context)
        if (!text.toLowerCase().contains(subText.toLowerCase())){
            return tv
        }
        val indexSubText = text.toLowerCase().indexOf(subText.toLowerCase())
        val ss = SpannableString(text)
        ss.apply {
            setSpan(TextAppearanceSpan(context,styleText),0,text.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            setSpan(
                TextAppearanceSpan(context,styleSubText),indexSubText,indexSubText + subText.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        tv.setText(ss, TextView.BufferType.SPANNABLE)
        return tv
    }

    fun sha1String(text: String): String {
        return MessageDigest.getInstance("SHA-1").digest(text.toByteArray()).joinToString("") { "%02x".format(it) }
    }

    fun isNumber(text: String): Boolean {
        return TextUtils.isDigitsOnly(text)
    }
}