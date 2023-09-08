package com.danphuong.utils.ext

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.Transformation
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.LinearLayout
import com.danphuong.utils.storge.DefaultUtilsPreference
import kotlin.math.roundToInt


fun Window.setMaxBrightScreen() {
    val layout: WindowManager.LayoutParams = this.attributes
    DefaultUtilsPreference.screenBrightness = layout.screenBrightness
    layout.screenBrightness = 1f
    this.attributes = layout
}

fun Window.currentBrightScreen() {
    val layout: WindowManager.LayoutParams = this.attributes
    DefaultUtilsPreference.screenBrightness = layout.screenBrightness
}

fun Window.setDefaultBrightScreen() {
    val layout: WindowManager.LayoutParams = this.attributes
    layout.screenBrightness = DefaultUtilsPreference.screenBrightness
    this.attributes = layout
}

fun View.expand() {
    val matchParentMeasureSpec =
        View.MeasureSpec.makeMeasureSpec((parent as View).width, View.MeasureSpec.EXACTLY)
    val wrapContentMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
    measure(matchParentMeasureSpec, wrapContentMeasureSpec)
    val targetHeight = measuredHeight

    // Older versions of android (pre API 21) cancel animations for views with a height of 0.
    layoutParams.height = 1
    visibility = View.VISIBLE
    val a: Animation = object : Animation() {
        override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
            layoutParams.height =
                if (interpolatedTime == 1f) LinearLayout.LayoutParams.WRAP_CONTENT else (targetHeight * interpolatedTime).toInt()
            requestLayout()
        }

        override fun willChangeBounds(): Boolean {
            return true
        }
    }

    a.setDuration(300)
    startAnimation(a)
}

fun View.collapse(duration: Long = 500) {
    val initialHeight = measuredHeight
    val a: Animation = object : Animation() {
        override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
            if (interpolatedTime == 1f) {
                visibility = View.GONE
            } else {
                layoutParams.height = initialHeight - (initialHeight * interpolatedTime).toInt()
                requestLayout()
            }
        }

        override fun willChangeBounds(): Boolean {
            return true
        }
    }

    a.setDuration(duration)
    startAnimation(a)
}


val Int.toPx get() = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP,
    this.toFloat(),
    Resources.getSystem().displayMetrics).toInt()

fun getWidthScreen(): Int {
     return Resources.getSystem().getDisplayMetrics().widthPixels;
}

fun getHeightScreen(): Int {
    return Resources.getSystem().getDisplayMetrics().heightPixels;
}
 fun dpToPx(dp: Float,context: Context): Float {
    val density = context.resources.displayMetrics.density
    return (dp * density).roundToInt().toFloat()
}
fun pxToDp(px: Int): Int {
    return (px / Resources.getSystem().displayMetrics.density).toInt()
}

fun Context.dpToPx(dp: Int): Int {
    val r: Resources = getResources()
    return Math.round(
        TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp.toFloat(),
            r.displayMetrics
        )
    )
}
fun View.hideKeyboard(activity: Activity?) {
    // Set up touch listener for non-text box views to hide keyboard.
    if (this !is EditText) {
        this?.setOnTouchListener { v, event ->
            hideSoftKeyboard(activity)
            false
        }
    }

    //If a layout container, iterate over children and seed recursion.
    if (this is ViewGroup) {
        for (i in 0 until this.childCount) {
            val innerView = this.getChildAt(i)
            innerView.hideKeyboard(activity)
        }
    }
}

fun hideSoftKeyboard(activity: Activity?) {
    if (activity == null) {
        return
    }
    val imm = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    val v = activity.currentFocus
    if (imm != null && v != null) {
        imm.hideSoftInputFromWindow(v.windowToken, 0)
    }
}
