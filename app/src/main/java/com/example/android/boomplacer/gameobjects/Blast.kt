package com.example.android.boomplacer.gameobjects

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import com.example.android.boomplacer.extensions.dpToPx
import com.example.android.boomplacer.math.Vector2

class Blast private constructor(
    private val paint: Paint,
    radius: Float,
    private val radiusDecreaseRate: Float,
    velocity: Vector2,
    position: Vector2,
    movePattern: MovePattern
) : GameObject(null, radius, velocity, position, movePattern) {
    companion object {
        fun simpleStaticBlast(paint: Paint) =
            normalizedBlast(paint, 128f, 64f, Vector2.zero(), Vector2.zero(), MovePattern.static)

        private fun normalizedBlast(
            paint: Paint,
            radius: Float,
            radiusDecreaseRate: Float,
            velocity: Vector2,
            position: Vector2,
            movePattern: MovePattern
        ): Blast {
            velocity.apply {
                x = dpToPx(x)
                y = dpToPx(y)
            }
            position.apply {
                x = dpToPx(x)
                y = dpToPx(y)
            }
            return Blast(
                paint,
                dpToPx(radius),
                radiusDecreaseRate,
                velocity,
                position,
                movePattern
            )
        }
    }

    override fun draw(canvas: Canvas) {
        canvas.drawCircle(position.x, position.y, radius, paint)
    }

    override fun updateState(fieldWidth: Int, fieldHeight: Int, secondsElapsed: Float): Boolean {
//        super.updateState(fieldWidth, fieldHeight, secondsElapsed)
        radius -= radiusDecreaseRate * secondsElapsed
        if (radius <= 0) {
            return false
        }
        return true
    }
}