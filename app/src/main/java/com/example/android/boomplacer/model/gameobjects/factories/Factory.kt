package com.example.android.boomplacer.model.gameobjects.factories

import android.graphics.Bitmap
import androidx.core.graphics.scale
import com.example.android.boomplacer.extensions.dpToPx
import com.example.android.boomplacer.math.Vector2

open class Factory {

    protected fun dpVectorToPxVector(dpVector: Vector2): Vector2 {
        dpVector.x = dpToPx(dpVector.x)
        dpVector.y = dpToPx(dpVector.y)
        return dpVector
    }

    protected fun bitmapToScaledBitmap(bitmap: Bitmap, radiusPx: Float): Bitmap {
        return bitmap.scale((radiusPx * 2).toInt(), (radiusPx * 2).toInt())
    }
}