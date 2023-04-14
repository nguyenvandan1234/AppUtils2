package com.locationvalue.ma2.view.mypage

import android.graphics.drawable.Drawable

class ItemMyPageWithIcon(var type: Int, var triple: Triple<String, Drawable?, () -> Unit>)

object MypageType {
    val TYPE_HEADER = 1;
    val TYPE_ITEM = 2;
    val TYPE_SPACE = 3;
}