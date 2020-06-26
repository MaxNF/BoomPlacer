package com.example.android.boomplacer.gameobjects

import android.graphics.Bitmap
import com.example.android.boomplacer.extensions.dpToPx
import com.example.android.boomplacer.math.Vector2

class Target private constructor(
    image: Bitmap,
    radius: Float,
    velocity: Vector2,
    position: Vector2,
    movePattern: MovePattern
) : GameObject(image, radius, velocity, position, movePattern) {
    companion object {
        fun simpleLinearTarget(image: Bitmap, position: Vector2) =
            normalizedTarget(image, 16f, Vector2(200f, 200f), position, MovePattern.linear)

        private fun normalizedTarget(
            image: Bitmap,
            radius: Float,
            velocity: Vector2,
            position: Vector2,
            movePattern: MovePattern
        ): Target {
            velocity.apply {
                x = dpToPx(x)
                y = dpToPx(y)
            }
            position.apply {
                x = dpToPx(x)
                y = dpToPx(y)
            }
            return Target(
                image,
                dpToPx(radius),
                velocity,
                position,
                movePattern
            )
        }
    }

    override fun updateState(fieldWidth: Int, fieldHeight: Int, secondsElapsed: Float): Boolean {
        return super.updateState(fieldWidth, fieldHeight, secondsElapsed)
    }
}