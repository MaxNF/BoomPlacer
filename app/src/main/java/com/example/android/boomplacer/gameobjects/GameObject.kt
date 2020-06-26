package com.example.android.boomplacer.gameobjects

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import androidx.core.graphics.scale
import com.example.android.boomplacer.math.Vector2
import java.lang.UnsupportedOperationException

abstract class GameObject protected constructor(
    image: Bitmap?,
    protected var radius: Float,
    protected val velocity: Vector2,
    var position: Vector2,
    protected val movePattern: MovePattern
) {
    private val TAG = "GameObject"
    private val normalizedImage: Bitmap? = image?.scale((radius * 2).toInt(), (radius * 2).toInt())

    open fun draw(canvas: Canvas) {
        if (normalizedImage == null) {
            throw UnsupportedOperationException("Bitmap can not be null!")
        }
        canvas.drawBitmap(normalizedImage, position.x - radius, position.y - radius, null)
    }

    private fun bounceLeft(fieldWidth: Int) {
        velocity.x = -velocity.x
        position.x = position.x - (position.x + radius - fieldWidth) * 2
    }

    private fun bounceRight() {
        velocity.x = -velocity.x
        position.x += -(position.x - radius)
    }

    private fun bounceUp(fieldHeight: Int) {
        velocity.y = -velocity.y
        position.y = position.y - (position.y + radius - fieldHeight) * 2
    }

    private fun bounceDown() {
        velocity.y = -velocity.y
        position.y += -(position.y - radius)
    }

    private fun topWallIntersected(): Boolean = position.y - radius < 0

    private fun bottomWallIntersected(fieldHeight: Int): Boolean =
        position.y + radius >= fieldHeight

    private fun leftWallIntersected(): Boolean = position.x - radius < 0

    private fun rightWallIntersected(fieldWidth: Int): Boolean = position.x + radius >= fieldWidth

    /**
     * @return true - if object still exists, false - if it has disappeared
     * */
    open fun updateState(fieldWidth: Int, fieldHeight: Int, secondsElapsed: Float): Boolean {
        position = movePattern.calculatePosition(position, velocity * secondsElapsed)
        if (topWallIntersected()) bounceDown()
        if (bottomWallIntersected(fieldHeight)) bounceUp(fieldHeight)
        if (leftWallIntersected()) bounceRight()
        if (rightWallIntersected(fieldWidth)) bounceLeft(fieldWidth)
        return true
    }
}