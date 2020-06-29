package com.example.android.boomplacer.model.gameobjects

import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.core.graphics.scale
import com.example.android.boomplacer.extensions.dpToPx
import com.example.android.boomplacer.game.ObjectManager
import com.example.android.boomplacer.math.Vector2
import com.example.android.boomplacer.model.gameobjects.movepatterns.MovePattern
import java.lang.UnsupportedOperationException

abstract class GameObject protected constructor(
    bitmap: Bitmap?,
    protected var radiusPx: Float,
    protected var velocityPx: Vector2,
    protected var positionPx: Vector2,
    protected var movePattern: MovePattern
) {
    private val TAG = "GameObject"

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
        movePattern.apply(this, secondsElapsed)
        if (topWallIntersected()) bounceDown()
        if (bottomWallIntersected(fieldHeight)) bounceUp(fieldHeight)
        if (leftWallIntersected()) bounceRight()
        if (rightWallIntersected(fieldWidth)) bounceLeft(fieldWidth)
        return false
    }
}