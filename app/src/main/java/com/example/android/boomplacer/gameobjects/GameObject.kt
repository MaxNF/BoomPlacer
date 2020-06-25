package com.example.android.boomplacer.gameobjects

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.PointF

abstract class GameObject(
    private val image: Bitmap,
    protected val radius: Float,
    protected val speed: Float,
    protected val position: PointF,
    protected val movePattern: MovePattern
) {

    fun draw(canvas: Canvas) {
        canvas.drawBitmap(image, position.x, position.y, null)
    }

    protected fun getSpeedPerFrame(timeForOneFrameMillis: Long): Float {
        return speed * (timeForOneFrameMillis / 1000f)
    }

    abstract fun updateState(timeForOneFrameMillis: Long)

//    private val leftSide get() = Pair(x - radius, y)
//    private val rightSide get() = Pair(x + radius, y)
//    private val upSide get() = Pair(x, y - radius)
//    private val downSide get() = Pair(x, y + radius)
}