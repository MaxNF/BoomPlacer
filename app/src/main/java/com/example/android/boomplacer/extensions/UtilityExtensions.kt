package com.example.android.boomplacer.extensions

import android.content.res.Resources

fun dpToPx(dp: Float): Float {
    return (dp * Resources.getSystem().displayMetrics.density)
}

fun pxToDp(px: Int): Float {
    return px / Resources.getSystem().displayMetrics.density
}