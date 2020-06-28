package com.example.android.boomplacer.model.gameobjects.base

import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.core.graphics.scale
import com.example.android.boomplacer.extensions.dpToPx
import com.example.android.boomplacer.game.ObjectManager
import com.example.android.boomplacer.math.Vector2
import java.lang.UnsupportedOperationException

abstract class GameObject protected constructor(
    bitmap: Bitmap?,
    radiusDp: Float,
    velocityDp: Vector2,
    positionDp: Vector2,
    var movePattern: MovePattern
) {
    private val TAG = "GameObject"
    var radiusPx: Float = dpToPx(radiusDp)
    var velocityPx: Vector2 = Vector2(dpToPx(velocityDp.x), dpToPx(velocityDp.y))
    var positionPx: Vector2 = Vector2(dpToPx(positionDp.x), dpToPx(positionDp.y))

    private val scaledBitmap: Bitmap? =
        bitmap?.scale((radiusPx * 2).toInt(), (radiusPx * 2).toInt())

    open fun draw(canvas: Canvas) {
        if (scaledBitmap == null) {
            throw UnsupportedOperationException("Bitmap can not be null!")
        }
        canvas.drawBitmap(
            scaledBitmap,
            positionPx.x - radiusPx,
            positionPx.y - radiusPx,
            null
        )
    }

    private fun bounceLeft(fieldWidth: Int) {
        velocityPx.x = -velocityPx.x
        positionPx.x =
            positionPx.x - (positionPx.x + radiusPx - fieldWidth) * 2
    }

    private fun bounceRight() {
        velocityPx.x = -velocityPx.x
        positionPx.x += -(positionPx.x - radiusPx)
    }

    private fun bounceUp(fieldHeight: Int) {
        velocityPx.y = -velocityPx.y
        positionPx.y =
            positionPx.y - (positionPx.y + radiusPx - fieldHeight) * 2
    }

    private fun bounceDown() {
        velocityPx.y = -velocityPx.y
        positionPx.y += -(positionPx.y - radiusPx)
    }

    private fun topWallIntersected(): Boolean = positionPx.y - radiusPx < 0

    private fun bottomWallIntersected(fieldHeight: Int): Boolean =
        positionPx.y + radiusPx >= fieldHeight

    private fun leftWallIntersected(): Boolean = positionPx.x - radiusPx < 0

    private fun rightWallIntersected(fieldWidth: Int): Boolean =
        positionPx.x + radiusPx >= fieldWidth

    /**
     * @return false - if object still exists, true - if it has been destroyed
     * */
    open fun updateState(
        fieldWidth: Int,
        fieldHeight: Int,
        secondsElapsed: Float,
        objectManager: ObjectManager
    ): Boolean {
        positionPx =
            movePattern.calculatePosition(positionPx, velocityPx * secondsElapsed)
        if (topWallIntersected()) bounceDown()
        if (bottomWallIntersected(fieldHeight)) bounceUp(fieldHeight)
        if (leftWallIntersected()) bounceRight()
        if (rightWallIntersected(fieldWidth)) bounceLeft(fieldWidth)
        return false
    }
}